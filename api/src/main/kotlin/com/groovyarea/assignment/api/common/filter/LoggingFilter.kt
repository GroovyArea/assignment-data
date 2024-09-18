package com.groovyarea.assignment.api.common.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.groovyarea.assignment.api.common.logback.HttpLog
import com.groovyarea.assignment.api.common.logback.Log
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.MDC
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class LoggingFilter(
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {

    companion object : Log {
        val cashNoteLogger: Logger
            get() = super.logger

        private val SUPPORTED_MEDIA_TYPE: List<String> = listOf(
            MediaType.TEXT_HTML_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        )

        private val EXCLUDE_LIST = arrayOf(
            Regex("/health/*"),
            Regex("/v3/api-docs/*"),
            Regex("/docs/*"),
            Regex("/swagger-ui/*")
        )
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return EXCLUDE_LIST.any { url -> request.requestURI.contains(url) }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        runCatching {
            val requestId = request.getHeader(HttpLog.X_REQUEST_ID) ?: request.requestId
            MDC.put(HttpLog.REQUEST_ID, requestId)
            loggingHttp(request, response, filterChain)
        }.onFailure { e ->
            cashNoteLogger.error(e.message, e)
        }.apply {
            MDC.clear()
        }.getOrThrow()
    }

    private fun loggingHttp(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val log = mutableMapOf<String, Any>()

        log[HttpLog.METHOD] = request.method
        log[HttpLog.URL] = request.requestURL
        log[HttpLog.HEADERS] = getHeaders(request)
        log[HttpLog.PARAMETERS] = getParams(request)

        val requestWrapper = ContentCachingRequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)
        filterChain.doFilter(requestWrapper, responseWrapper)
        if (hasPayload(request)) {
            log[HttpLog.CONTENT_TYPE] = request.contentType
            log[HttpLog.REQUEST] = cashNoteLogger.jsonLogFormat(requestWrapper.contentAsByteArray)
        }
        if (isSupportedMediaType(responseWrapper.contentType)) {
            log[HttpLog.RESPONSE] = cashNoteLogger.jsonLogFormat(responseWrapper.contentAsByteArray)
        }
        log[HttpLog.STATUS] = responseWrapper.status
        responseWrapper.copyBodyToResponse()

        cashNoteLogger.info(log)
    }

    private fun isSupportedMediaType(mediaTypeValue: String?): Boolean {
        if (mediaTypeValue == null) return false
        return SUPPORTED_MEDIA_TYPE.any { supportedValue: String -> mediaTypeValue.contains(supportedValue) }
    }

    private fun hasPayload(request: HttpServletRequest): Boolean {
        val contentType = request.contentType
        if (contentType == null || request.contentLength < 1) return false
        return isSupportedMediaType(contentType)
    }

    private fun getHeaders(request: HttpServletRequest): Map<String, Any> {
        val headerMap: MutableMap<String, Any> = HashMap()
        val headerArray = request.headerNames
        while (headerArray.hasMoreElements()) {
            val headerName = headerArray.nextElement()
            headerMap[headerName] = request.getHeader(headerName)
        }
        return headerMap
    }

    private fun getParams(request: HttpServletRequest): String {
        return request.parameterMap.entries.joinToString("&") { (key, value) ->
            "$key=${value.first()}"
        }
    }
}
