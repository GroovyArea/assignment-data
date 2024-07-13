package com.groovyarea.assignment.datatransfer.infrastructure.http.community.client

import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
interface CommunityClient {

    @PostExchange("/card-transactions")
    fun sendCardTransactions(
        @RequestBody request: List<CardTransactionRequest>,
    )
}
