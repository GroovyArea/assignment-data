package com.groovyarea.assignment.cashnote.domain.service

import com.groovyarea.assignment.cashnote.common.exception.NotFoundException
import com.groovyarea.assignment.cashnote.domain.entity.table.ConnectionAgreement
import com.groovyarea.assignment.cashnote.domain.exception.NOT_FOUND_CONNECTION_AGREEMENT
import com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository.ConnectionAgreementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ConnectionAgreementService(
    private val connectionAgreementRepository: ConnectionAgreementRepository,
) {

    @Transactional(readOnly = true)
    fun getAgreement(
        registrationNumber: String,
    ): ConnectionAgreement {
        return connectionAgreementRepository.findByRegistrationNumber(
            registrationNumber = registrationNumber
        ) ?: throw NotFoundException(message = NOT_FOUND_CONNECTION_AGREEMENT)
    }

    @Transactional
    fun agree(
        registrationNumber: String,
        hasBusiness: Boolean,
    ) {
        val connectionAgreement = connectionAgreementRepository.findByRegistrationNumber(
            registrationNumber = registrationNumber
        )

        if (connectionAgreement != null) {
            if (hasBusiness) {
                connectionAgreement.agree()
            }
        } else {
            val newConnectionAgreement = createNewConnectionAgreement(
                registrationNumber = registrationNumber,
                hasBusiness = hasBusiness
            )
            connectionAgreementRepository.save(newConnectionAgreement)
        }
    }

    private fun createNewConnectionAgreement(
        registrationNumber: String,
        hasBusiness: Boolean,
    ): ConnectionAgreement {
        return if (hasBusiness) {
            ConnectionAgreement(
                registrationNumber = registrationNumber,
                isConnectionAgreed = true,
                connectionAgreedAt = LocalDateTime.now()
            )
        } else {
            ConnectionAgreement(
                registrationNumber = registrationNumber
            )
        }
    }
}
