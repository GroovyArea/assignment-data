package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.common.pagination.PageDTO
import com.groovyarea.assignment.datatransfer.domain.service.dto.GetPagedDataTransferAgreement
import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.DataTransferAgreementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DataTransferAgreementQueryService(
    private val dataTransferAgreementRepository: DataTransferAgreementRepository,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }

    @Transactional(readOnly = true)
    fun getPagedDataTransferAgreementsOfAgreed(
        currentPageNumber: Int,
    ): GetPagedDataTransferAgreement {
        val pageDTO = PageDTO(
            currentPageNumber = currentPageNumber,
            currentPageSize = DEFAULT_PAGE_SIZE
        )

        val pagedDataTransferAgreements =
            dataTransferAgreementRepository.findAllByDataTransferAgreedTrueOrderById(
                pageable = pageDTO,
            )

        val nextPageNumber = pagedDataTransferAgreements.pageable.next().pageNumber
        val contents = pagedDataTransferAgreements.content

        return GetPagedDataTransferAgreement(
            nextPageNumber = nextPageNumber,
            contents = contents
        )
    }
}
