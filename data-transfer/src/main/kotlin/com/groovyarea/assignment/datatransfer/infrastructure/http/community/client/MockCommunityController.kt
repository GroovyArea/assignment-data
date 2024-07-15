package com.groovyarea.assignment.datatransfer.infrastructure.http.community.client

import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest
import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.response.CommunityResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * mock 공동체 API controller
 */
@RestController
class MockCommunityController {

    @PostMapping("/card-transactions")
    fun sendCardTransactions(
        @RequestBody request: List<CardTransactionRequest>,
    ): CommunityResponse<Nothing> {
        return CommunityResponse()
    }
}
