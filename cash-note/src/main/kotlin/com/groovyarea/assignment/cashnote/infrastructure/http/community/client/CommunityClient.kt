package com.groovyarea.assignment.cashnote.infrastructure.http.community.client

import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.response.HasBusinessResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.PostExchange

interface CommunityClient {

    @GetExchange("/has-business")
    fun hasBusiness(
        @RequestParam registrationNumber: String,
    ): HasBusinessResponse

    @PostExchange("/register-data-communication")
    fun registerDataCommunication(
        @RequestBody request: RegisterDataCommunicationRequest,
    )

    @PostExchange("/card-transactions")
    fun sendCardTransactions()
}
