package com.groovyarea.assignment.api.application.service

import com.groovyarea.assignment.api.application.dto.AgreeConnectionCheckRequest
import com.groovyarea.assignment.api.application.dto.AgreeConnectionCheckResponse
import com.groovyarea.assignment.api.application.dto.AgreeDataTransferRequest
import com.groovyarea.assignment.api.application.exception.INVALID_CONNECTION_AGREEMENT_STATUS
import com.groovyarea.assignment.api.application.service.community.CommunityService
import com.groovyarea.assignment.api.common.exception.InvalidException
import com.groovyarea.assignment.api.domain.service.ConnectionAgreementService
import com.groovyarea.assignment.api.domain.service.DataTransferAgreementService
import com.groovyarea.assignment.api.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest
import org.springframework.stereotype.Service

@Service
class ApplicationService(
    private val dataTransferApplicationService: DataTransferApplicationService,
    private val communityService: CommunityService,
    private val connectionAgreementService: ConnectionAgreementService,
    private val dataTransferAgreementService: DataTransferAgreementService,
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

    fun agreeDataTransfer(
        request: AgreeDataTransferRequest,
    ) {
        val registrationNumber = request.registrationNumber

        val connectionAgreement = connectionAgreementService.getAgreement(
            registrationNumber = registrationNumber
        )

        if (connectionAgreement.isNotAgreed()) {
            throw InvalidException(message = INVALID_CONNECTION_AGREEMENT_STATUS)
        } else {
            val registerDataCommunicationRequest = RegisterDataCommunicationRequest(
                registrationNumber = registrationNumber
            )
            communityService.registerDataCommunication(
                registerDataCommunicationRequest = registerDataCommunicationRequest
            )
            dataTransferAgreementService.agree(
                registrationNumber = registrationNumber
            )
            dataTransferApplicationService.transferFirstData(
                registrationNumber = registrationNumber
            )
        }
    }
}
