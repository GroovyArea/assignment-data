package com.groovyarea.assignment.cashnote.domain.service

import com.groovyarea.assignment.cashnote.domain.entity.table.DataTransferAgreement
import com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository.DataTransferAgreementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
