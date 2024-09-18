package com.groovyarea.assignment.api.infrastructure.http.community.dto.request

data class RegisterDataCommunicationRequest(
    val registrationNumber: String,
    val agreedType: String = "Y",
)
