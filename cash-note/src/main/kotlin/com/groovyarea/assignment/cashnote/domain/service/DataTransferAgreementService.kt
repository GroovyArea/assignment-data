package com.groovyarea.assignment.cashnote.domain.service

import com.groovyarea.assignment.cashnote.domain.entity.table.DataTransferAgreement
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DataTransferAgreementService(
    private val dataTransferAgreementRepository: DataTransferAgreementRepository,
) {

    @Transactional
    fun agree(
        registrationNumber: String,
    ) {
        val dataTransferAgreement = DataTransferAgreement(
            registrationNumber = registrationNumber
        )
        dataTransferAgreementRepository.save(dataTransferAgreement)
    }
}
