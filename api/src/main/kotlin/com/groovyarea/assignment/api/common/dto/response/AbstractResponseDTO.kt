package com.groovyarea.assignment.api.common.dto.response

open class AbstractResponseDTO<T>(
    open val meta: ResponseDTO.Meta,
    open val data: T? = null,
)
