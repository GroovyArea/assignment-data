package com.groovyarea.assignment.api.domain.service

import com.groovyarea.assignment.api.domain.entity.table.DataTransferAgreement
import com.groovyarea.assignment.api.infrastructure.db.jpa.repository.DataTransferAgreementRepository
import org.springframework.stereotype.Service

@Service
class DataTransferAgreementService(
    private val dataTransferAgreementRepository: DataTransferAgreementRepository,
) {

    fun agree(
        registrationNumber: String,
    ) {
        val dataTransferAgreement = DataTransferAgreement(
            registrationNumber = registrationNumber
        )
        dataTransferAgreementRepository.save(dataTransferAgreement)
    }
}
