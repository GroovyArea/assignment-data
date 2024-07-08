package com.groovyarea.assignment.cashnote.application.service.community

import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest

interface CommunityService {

    fun hasBusiness(
        registrationNumber: String,
    ): Boolean

    fun registerDataCommunication(
        registerDataCommunicationRequest: RegisterDataCommunicationRequest,
    )

    fun sendCardTransactions(
        cardTransactions: List<CardTransaction>,
    )
}
