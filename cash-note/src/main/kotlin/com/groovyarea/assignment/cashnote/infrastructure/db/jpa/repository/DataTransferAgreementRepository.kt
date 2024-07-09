package com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository

import com.groovyarea.assignment.cashnote.common.entity.ID
import com.groovyarea.assignment.cashnote.domain.entity.table.DataTransferAgreement
import org.springframework.data.jpa.repository.JpaRepository

interface DataTransferAgreementRepository : JpaRepository<DataTransferAgreement, ID>
