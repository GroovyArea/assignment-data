package com.groovyarea.assignment.datatransfer.infrastructure.http.config

import com.groovyarea.assignment.datatransfer.common.logback.Log
import com.groovyarea.assignment.datatransfer.infrastructure.http.community.client.CommunityClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatusCode
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import java.net.URI

@Configuration
class CommunityHttpConfig {

    @Value("\${external.community.url}")
    private lateinit var communityUrl: String

    companion object : Log

    @Bean
    fun communityClient(): CommunityClient {
        val restClient = createClient()
        val adapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(adapter).build()
        return factory.createClient(CommunityClient::class.java)
    }

    private fun createClient(): RestClient =
        RestClient.builder()
            .baseUrl(communityUrl)
            .requestInterceptor { request, body, execution ->
                logRequest(request.uri, request.method, request.headers, body)
                val response = execution.execute(request, body)
                logResponse(response)
                response
            }
            .defaultStatusHandler(
                HttpStatusCode::is4xxClientError
            ) { _, response ->
                logger.error("Client Error Code={}", response.statusCode)
                logger.error("Client Error Message={}", String(response.body.readAllBytes()))
            }
            .defaultStatusHandler(
                HttpStatusCode::is5xxServerError
            ) { _, response ->
                logger.error("Server Error Code={}", response.statusCode)
                logger.error("Server Error Message={}", String(response.body.readAllBytes()))
            }
            .build()

    private fun logRequest(
        uri: URI,
        method: HttpMethod,
        headers: HttpHeaders,
        body: ByteArray?,
    ) {
        logger.info("===== Request =====")
        logger.info("URI: {}", uri)
        logger.info("Method: {}", method)
        logger.info("Headers: {}", headers)
        logger.info("Body: {}", body?.let { String(it, Charsets.UTF_8) })
    }

    private fun logResponse(
        response: ClientHttpResponse,
    ) {
        logger.info("===== Response =====")
        logger.info("Status code: {}", response.statusCode)
        logger.info("Headers: {}", response.headers)
        val responseBody = response.body.readAllBytes().toString(Charsets.UTF_8)
        logger.info("Body: {}", responseBody)
    }
}
