package com.groovyarea.assignment.datatransfer.external.service

import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest

interface CommunityService {

    fun sendTransaction(
        cardTransactionRequests: List<CardTransactionRequest>,
    )
}
