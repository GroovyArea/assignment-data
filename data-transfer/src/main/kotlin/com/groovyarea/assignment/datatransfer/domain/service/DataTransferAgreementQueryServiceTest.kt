package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.DataTransferAgreementRepository

private val repository = mockk<DataTransferAgreementRepository>()

@InjectMockks
class DataTransferAgreementQueryServiceTest