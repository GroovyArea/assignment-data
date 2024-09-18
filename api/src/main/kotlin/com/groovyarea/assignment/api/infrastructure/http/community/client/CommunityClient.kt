package com.groovyarea.assignment.api.infrastructure.http.community.client

import com.groovyarea.assignment.api.infrastructure.http.community.dto.request.CardTransactionRequest
import com.groovyarea.assignment.api.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest
import com.groovyarea.assignment.api.infrastructure.http.community.dto.response.CommunityResponse
import com.groovyarea.assignment.api.infrastructure.http.community.dto.response.HasBusinessResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
interface CommunityClient {

    @GetExchange("/has-business")
    fun hasBusiness(
        @RequestParam registrationNumber: String,
    ): CommunityResponse<HasBusinessResponse>

    @PostExchange("/register-data-communication")
    fun registerDataCommunication(
        @RequestBody request: RegisterDataCommunicationRequest,
    ): CommunityResponse<Nothing>

    @PostExchange("/card-transactions")
    fun sendCardTransactions(
        @RequestBody request: List<CardTransactionRequest>,
    ): CommunityResponse<Nothing>
}
