package com.groovyarea.assignment.api.common.model

import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.http.HttpStatus

enum class MetaCode(private var code: String) {
    SUCCESS("20000000"),
    CREATED("20100000"),
    ACCEPTED("20200000"),
    NO_CONTENT("20400000"),
    INVALID_PARAMETER("40000000"),
    AUTHENTICATION_FAILED("40100000"),
    FORBIDDEN("40300000"),
    NOT_FOUND("40400000"),
    METHOD_NOW_ALLOWED("40500000"),
    NOT_ACCEPTABLE("40600000"),
    REQUEST_TIMEOUT("40800000"),
    CONFLICT("40900000"),
    UNSUPPORTED_MEDIA_TYPE("41500000"),
    UNPROCESSABLE_ENTITY("42200000"),
    INTERNAL_SERVER_ERROR("50000000");

    @JsonValue
    override fun toString(): String {
        return this.code
    }
    companion object {
        @Suppress("CyclomaticComplexMethod")
        fun valueFrom(status: HttpStatus) = when (status) {
            HttpStatus.OK -> SUCCESS
            HttpStatus.CREATED -> CREATED
            HttpStatus.ACCEPTED -> ACCEPTED
            HttpStatus.NO_CONTENT -> NO_CONTENT
            HttpStatus.BAD_REQUEST -> INVALID_PARAMETER
            HttpStatus.UNAUTHORIZED -> AUTHENTICATION_FAILED
            HttpStatus.FORBIDDEN -> FORBIDDEN
            HttpStatus.NOT_FOUND -> NOT_FOUND
            HttpStatus.METHOD_NOT_ALLOWED -> METHOD_NOW_ALLOWED
            HttpStatus.NOT_ACCEPTABLE -> NOT_ACCEPTABLE
            HttpStatus.UNSUPPORTED_MEDIA_TYPE -> UNSUPPORTED_MEDIA_TYPE
            HttpStatus.CONFLICT -> CONFLICT
            HttpStatus.INTERNAL_SERVER_ERROR -> INTERNAL_SERVER_ERROR
            HttpStatus.UNPROCESSABLE_ENTITY -> UNPROCESSABLE_ENTITY
            else -> throw IllegalArgumentException("Please provide correct status.")
        }
    }
}
