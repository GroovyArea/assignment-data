package com.groovyarea.assignment.cashnote.infrastructure.http.community.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.groovyarea.assignment.cashnote.common.logback.Log
import com.groovyarea.assignment.cashnote.infrastructure.http.community.client.CommunityClient
import com.groovyarea.assignment.cashnote.infrastructure.http.community.exception.CommunityClientException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatusCode
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

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

    private fun createClient(): RestClient {
        val mapper = jacksonObjectMapper().registerModule(kotlinModule())
        val converter = MappingJackson2HttpMessageConverter(mapper)
        return RestClient.builder()
            .baseUrl(communityUrl)
            .messageConverters { it.add(converter) }
            .defaultStatusHandler(
                HttpStatusCode::is4xxClientError
            ) { _, response ->
                logger.error("Client Error Code={}", response.statusCode)
                logger.error("Client Error Message={}", String(response.body.readAllBytes()))
                throw CommunityClientException()
            }
            .defaultStatusHandler(
                HttpStatusCode::is5xxServerError
            ) { _, response ->
                logger.error("Server Error Code={}", response.statusCode)
                logger.error("Server Error Message={}", String(response.body.readAllBytes()))
                throw CommunityClientException()
            }
            .build()
    }
}
