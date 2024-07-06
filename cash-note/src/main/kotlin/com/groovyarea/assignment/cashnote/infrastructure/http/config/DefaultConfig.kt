package com.groovyarea.assignment.cashnote.infrastructure.http.config

import com.groovyarea.assignment.cashnote.infrastructure.http.CustomErrorDecoder
import com.groovyarea.assignment.cashnote.infrastructure.http.FeignClientCustomLogger
import com.groovyarea.assignment.cashnote.infrastructure.http.TraceHeaderInterceptor
import feign.Logger
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DefaultConfig {

    @Bean
    fun customLogger(): Logger = FeignClientCustomLogger()

    @Bean
    fun defaultRetryPolicy(): Retryer {
        return Retryer.Default()
    }

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return CustomErrorDecoder()
    }

    @Bean
    fun headerInterceptor(): TraceHeaderInterceptor {
        return TraceHeaderInterceptor()
    }
}
