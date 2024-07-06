package com.groovyarea.assignment.cashnote.infrastructure.http

import com.groovyarea.assignment.cashnote.common.logback.HttpLog
import com.groovyarea.assignment.cashnote.common.logback.Log
import feign.Logger
import feign.Request
import feign.Response
import feign.Util
import org.springframework.http.HttpStatus
import java.io.IOException

class FeignClientCustomLogger : Logger() {

    companion object : Log {
        private const val ERROR: String = "error"
    }

    override fun logRequest(configKey: String, logLevel: Level, request: Request) {
        val log = mapOf<String, String>(
            HttpLog.REMOTE_SERVICE to configKey,
            HttpLog.METHOD to request.httpMethod().name,
            HttpLog.URL to request.url(),
            HttpLog.HEADERS to getHeaders(request).toString(),
            HttpLog.REQUEST to getRequestBody(request)
        )
        logger.info(log)
    }

    override fun logAndRebufferResponse(
        configKey: String,
        logLevel: Level,
        response: Response,
        elapsedTime: Long
    ): Response {
        var chainingResponse = response
        var responseBody: Any = ""
        if (hasResponseBody(response)) {
            val bodyData: ByteArray = Util.toByteArray(response.body().asInputStream())
            responseBody = logger.jsonLogFormat(bodyData)
            chainingResponse = response.toBuilder().body(bodyData).build()
        }
        val log = mapOf(
            HttpLog.REMOTE_SERVICE to configKey,
            HttpLog.RESPONSE to responseBody,
            HttpLog.STATUS to response.status()
        )

        logger.info(log)
        return chainingResponse
    }

    override fun logIOException(
        configKey: String,
        logLevel: Level,
        ioe: IOException,
        elapsedTime: Long
    ): IOException {
        val errorMessage = "ERROR ${ioe.javaClass.simpleName}: ${ioe.message}"
        val log = mapOf(
            HttpLog.REMOTE_SERVICE to configKey,
            ERROR to errorMessage
        )
        logger.info(log)
        return ioe
    }

    override fun log(configKey: String, format: String, vararg args: Any?) = Unit

    private fun getHeaders(request: Request): Map<String, Any> {
        val headerMap: MutableMap<String, Any> = HashMap()
        request.headers().forEach { entry ->
            headerMap[entry.key] = entry.value.first()
        }
        return headerMap
    }

    private fun getRequestBody(request: Request): String {
        return request.charset()?.let { String(request.body(), request.charset()) } ?: ""
    }

    private fun hasResponseBody(response: Response): Boolean {
        val status: Int = response.status()
        return response.body() != null &&
            !(status == HttpStatus.NO_CONTENT.value() || status == HttpStatus.RESET_CONTENT.value())
    }
}
