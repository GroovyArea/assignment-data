package com.groovyarea.assignment.cashnote.infrastructure.http

import feign.FeignException
import feign.Response
import feign.RetryableException
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus

class CustomErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        val exception = FeignException.errorStatus(methodKey, response)
        val retryStatus = listOf(
            HttpStatus.BAD_GATEWAY.value(),
            HttpStatus.SERVICE_UNAVAILABLE.value(),
            HttpStatus.GATEWAY_TIMEOUT.value()
        )

        return when (response.status()) {
            in retryStatus -> RetryableException(
                response.status(),
                exception.message,
                response.request().httpMethod(),
                exception,
                null,
                response.request()
            )
            else -> exception
        }
    }
}
