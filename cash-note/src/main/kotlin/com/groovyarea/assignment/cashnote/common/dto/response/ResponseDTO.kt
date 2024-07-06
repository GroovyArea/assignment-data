package com.groovyarea.assignment.cashnote.common.dto.response

import com.groovyarea.assignment.cashnote.common.model.MetaCode

data class ResponseDTO<T>(
    val meta: Meta,
    val data: T? = null,
) {
    companion object {
        fun <T> success(meta: Meta, data: T? = null): ResponseDTO<T> = ResponseDTO(meta, data)
        fun <T> success(metaCode: MetaCode, data: T? = null): ResponseDTO<T> = success(Meta(code = metaCode), data)
        fun <T> success(data: T? = null): ResponseDTO<T> = success(MetaCode.SUCCESS, data)
        fun <T> created(data: T? = null): ResponseDTO<T> = ResponseDTO(meta = Meta(MetaCode.CREATED), data)
    }

    data class Meta(
        val code: MetaCode,
        val type: String? = code.name.lowercase(),
        val message: String? = null,
    )
}
