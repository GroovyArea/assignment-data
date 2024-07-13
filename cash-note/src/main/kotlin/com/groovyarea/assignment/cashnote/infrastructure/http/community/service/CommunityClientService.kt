package com.groovyarea.assignment.cashnote.infrastructure.http.community.service

import com.groovyarea.assignment.cashnote.application.service.community.CommunityService
import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction
import com.groovyarea.assignment.cashnote.infrastructure.http.community.client.CommunityClient
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest
import com.groovyarea.assignment.cashnote.infrastructure.http.community.mapper.CardTransactionMapper
import org.springframework.stereotype.Service

@Service
class CommunityClientService(
    private val communityClient: CommunityClient,
) : CommunityService {

    override fun hasBusiness(
        registrationNumber: String,
    ): Boolean {
        val response = communityClient.hasBusiness(
            registrationNumber = registrationNumber
        )

        return response.data?.result ?: false
    }

    override fun registerDataCommunication(
        registerDataCommunicationRequest: RegisterDataCommunicationRequest,
    ) {
        communityClient.registerDataCommunication(
            request = registerDataCommunicationRequest
        )
    }

    override fun sendCardTransactions(
        cardTransactions: List<CardTransaction>,
    ) {
        val requests = cardTransactions.map {
            CardTransactionMapper.INSTANCE.convertToCardTransactionRequest(
                cardTransaction = it
            )
        }

        communityClient.sendCardTransactions(
            request = requests
        )
    }
}
