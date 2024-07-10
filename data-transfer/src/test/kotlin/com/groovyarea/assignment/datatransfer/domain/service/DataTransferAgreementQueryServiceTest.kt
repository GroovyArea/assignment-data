package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.common.pagination.PageDTO
import com.groovyarea.assignment.datatransfer.domain.entity.table.DataTransferAgreement
import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.DataTransferAgreementRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

private val repository = mockk<DataTransferAgreementRepository>()

@InjectMockKs
private val dataTransferAgreementQueryService = DataTransferAgreementQueryService(
    dataTransferAgreementRepository = repository
)

class DataTransferAgreementQueryServiceTest : BehaviorSpec({

    given("데이터 전송에 동의한 동의 목록을 페이징 처리하여, 쿼리하기 위해") {
        val currentPageNumber = 1
        val currentPageSize = 10
        val registrationNumber = "214-23-12345"
        val totalCount: Long = 20

        val content = listOf(
            DataTransferAgreement(
                registrationNumber = registrationNumber,
                dataTransferAgreed = true,
                dataTransferAgreedAt = LocalDateTime.now(),
            )
        )

        val pageDTO = PageDTO(
            currentPageNumber = currentPageNumber,
            currentPageSize = currentPageSize
        )

        val pageImpl = PageImpl(
            content,
            pageDTO,
            totalCount
        )

        every {
            repository.findAllByDataTransferAgreedTrueOrderById(
                pageable = pageDTO
            )
        } returns pageImpl

        `when`("함수를 호출하면") {
            val getPagedDataTransferAgreement =
                dataTransferAgreementQueryService.getPagedDataTransferAgreementsOfAgreed(
                    currentPageNumber = currentPageNumber
                )

            then("쿼리 결과를 반환한다.") {
                getPagedDataTransferAgreement.nextPageNumber shouldBe 2
                getPagedDataTransferAgreement.contents.size shouldBe 1
            }
        }
    }
})
