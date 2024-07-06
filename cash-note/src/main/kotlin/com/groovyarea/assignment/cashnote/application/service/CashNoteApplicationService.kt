package com.groovyarea.assignment.cashnote.application.service

import com.groovyarea.assignment.cashnote.application.dto.AgreeConnectionCheckRequest
import com.groovyarea.assignment.cashnote.application.dto.AgreeConnectionCheckResponse
import com.groovyarea.assignment.cashnote.application.service.community.CommunityService
import com.groovyarea.assignment.cashnote.domain.service.ConnectionAgreementService
import org.springframework.stereotype.Service

@Service
class CashNoteApplicationService(
    private val communityService: CommunityService,
    private val connectionAgreementService: ConnectionAgreementService,
) {

    fun agreeConnectionCheck(
        request: AgreeConnectionCheckRequest,
    ): AgreeConnectionCheckResponse {
        val registrationNumber = request.registrationNumber
        val hasBusiness = communityService.hasBusiness(
            registrationNumber = registrationNumber
        )

        connectionAgreementService.agree(
            registrationNumber = registrationNumber,
            hasBusiness = hasBusiness
        )

        return AgreeConnectionCheckResponse(
            result = hasBusiness
        )
    }
}
