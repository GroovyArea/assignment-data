package com.groovyarea.assignment.cashnote.infrastructure.http.community.client

import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.CardTransactionRequest
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.response.CommunityResponse
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.response.HasBusinessResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * mock 공동체 API controller
 */
@RestController
class MockCommunityController {

    @GetMapping("/has-business")
    fun hasBusiness(
        @RequestParam registrationNumber: String,
    ): CommunityResponse<HasBusinessResponse> {
        return CommunityResponse(
            data = HasBusinessResponse(true)
        )
    }

    @PostMapping("/register-data-communication")
    fun registerDataCommunication(
        @RequestBody request: RegisterDataCommunicationRequest,
    ): CommunityResponse<Nothing> {
        return CommunityResponse()
    }

    @PostMapping("/card-transactions")
    fun sendCardTransactions(
        @RequestBody request: List<CardTransactionRequest>,
    ): CommunityResponse<Nothing> {
        return CommunityResponse()
    }
}
