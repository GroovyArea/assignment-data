package com.groovyarea.assignment.datatransfer.infrastructure.db.jpa

import com.groovyarea.assignment.datatransfer.common.entity.ID
import com.groovyarea.assignment.datatransfer.domain.entity.table.DataTransferAgreement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DataTransferAgreementRepository : JpaRepository<DataTransferAgreement, ID> {

    fun findAllByDataTransferAgreedTrueOrderById(
        pageable: Pageable,
    ): Page<DataTransferAgreement>
}
