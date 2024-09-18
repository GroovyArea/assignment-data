package com.groovyarea.assignment.api.infrastructure.db.jpa.repository

import com.groovyarea.assignment.api.common.entity.ID
import com.groovyarea.assignment.api.domain.entity.table.DataTransferAgreement
import org.springframework.data.jpa.repository.JpaRepository

interface DataTransferAgreementRepository : JpaRepository<DataTransferAgreement, ID>
