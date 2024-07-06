package com.groovyarea.assignment.datatransfer.infrastructure.http.community.client

import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest
import com.groovyarea.assignment.datatransfer.infrastructure.http.config.DefaultConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "community-client",
    configuration = [DefaultConfig::class]
)
interface CommunityClient {

    @PostMapping("/card-transactions")
    fun sendCardTransactions(
        @RequestBody request: List<CardTransactionRequest>,
    )
}
