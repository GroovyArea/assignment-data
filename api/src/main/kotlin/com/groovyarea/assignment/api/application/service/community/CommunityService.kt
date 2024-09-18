package com.groovyarea.assignment.api.application.service.community

import com.groovyarea.assignment.api.domain.entity.table.CardTransaction
import com.groovyarea.assignment.api.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest

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
