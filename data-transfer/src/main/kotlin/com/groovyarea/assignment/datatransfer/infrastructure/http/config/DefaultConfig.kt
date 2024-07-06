package com.groovyarea.assignment.datatransfer.infrastructure.http.config

import com.groovyarea.assignment.datatransfer.infrastructure.http.CustomErrorDecoder
import com.groovyarea.assignment.datatransfer.infrastructure.http.FeignClientCustomLogger
import com.groovyarea.assignment.datatransfer.infrastructure.http.TraceHeaderInterceptor
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
