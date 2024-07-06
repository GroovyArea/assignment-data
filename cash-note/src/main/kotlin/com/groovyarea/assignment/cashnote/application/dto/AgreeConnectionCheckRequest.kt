package com.groovyarea.assignment.cashnote.application.dto

import jakarta.validation.constraints.NotBlank

data class AgreeConnectionCheckRequest(
    @field:NotBlank(message = "registrationNumber 필드가 누락되었습니다.")
    val registrationNumber: String,
)
