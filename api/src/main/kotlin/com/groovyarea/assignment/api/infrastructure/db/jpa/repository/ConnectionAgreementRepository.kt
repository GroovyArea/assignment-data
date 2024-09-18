package com.groovyarea.assignment.api.infrastructure.db.jpa.repository

import com.groovyarea.assignment.api.common.entity.ID
import com.groovyarea.assignment.api.domain.entity.table.ConnectionAgreement
import org.springframework.data.jpa.repository.JpaRepository

interface ConnectionAgreementRepository : JpaRepository<ConnectionAgreement, ID> {

    fun findByRegistrationNumber(registrationNumber: String): ConnectionAgreement?
}
