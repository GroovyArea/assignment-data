package com.groovyarea.assignment.datatransfer.domain.service.dto

import com.groovyarea.assignment.datatransfer.domain.entity.table.DataTransferAgreement

data class GetPagedDataTransferAgreement(
    val nextPageNumber: Int,
    val contents: List<DataTransferAgreement>
)
