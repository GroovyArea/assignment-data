package com.groovyarea.assignment.datatransfer.infrastructure.http.community.client

import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest
import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.response.CommunityResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * mock 공동체 API controller
 */
class MockCommunityController {

    @PostMapping("/card-transactions")
    fun sendCardTransactions(
        @RequestBody request: List<CardTransactionRequest>,
    ): CommunityResponse<Nothing> {
        return CommunityResponse()
    }
}
