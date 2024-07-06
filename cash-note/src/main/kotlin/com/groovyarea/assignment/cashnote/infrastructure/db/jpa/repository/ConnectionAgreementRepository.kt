package com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository

import com.groovyarea.assignment.cashnote.common.entity.ID
import com.groovyarea.assignment.cashnote.domain.entity.table.ConnectionAgreement
import org.springframework.data.jpa.repository.JpaRepository

interface ConnectionAgreementRepository : JpaRepository<ConnectionAgreement, ID> {

    fun findByRegistrationNumber(registrationNumber: String): ConnectionAgreement?
}
