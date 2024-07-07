package com.groovyarea.assignment.datatransfer.infrastructure.http.community.service

import com.groovyarea.assignment.datatransfer.external.service.CommunityService
import com.groovyarea.assignment.datatransfer.infrastructure.http.community.client.CommunityClient
import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest
import org.springframework.stereotype.Service

@Service
class CommunityClientService(
    private val communityClient: CommunityClient,
) : CommunityService {

    override fun sendTransaction(
        cardTransactionRequests: List<CardTransactionRequest>,
    ) {
        communityClient.sendCardTransactions(
            request = cardTransactionRequests
        )
    }
}
