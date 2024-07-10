package com.groovyarea.assignment.cashnote.domain.service

import com.groovyarea.assignment.cashnote.common.pagination.PageDTO
import com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository.CardTransactionRepository
import com.groovyarea.assignment.cashnote.stub.dummyCardTransaction
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val repository = mockk<CardTransactionRepository>()

@InjectMockKs
private val cardTransactionQueryService = CardTransactionQueryService(
    cardTransactionRepository = repository
)

class CardTransactionQueryServiceTest : BehaviorSpec({

    given("특정 범위의 Card Data 를 페이징 처리하여, 쿼리하기 위해") {
        val currentPageNumber = 1
        val currentPageSize = 10
        val registrationNumber = "214-23-12345"
        val endDate = LocalDateTime.now()
        val startDate = endDate.minus(6, ChronoUnit.MONTHS)
        val totalCount: Long = 20

        val content = listOf(
            dummyCardTransaction()
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
            repository.findAllByRegistrationNumberAndCreatedAtBetween(
                registrationNumber = registrationNumber,
                startDateTime = startDate,
                endDateTime = endDate,
                pageable = pageDTO
            )
        } returns pageImpl

        `when`("함수를 호출하면") {
            val getPagedCardTransaction = cardTransactionQueryService.getCardTransactionsBetween(
                currentPageNumber = currentPageNumber,
                startDate = startDate,
                endDate = endDate,
                registrationNumber = registrationNumber
            )

            then("쿼리 결과를 반환한다.") {
                getPagedCardTransaction.nextPageNumber shouldBe 2
                getPagedCardTransaction.contents.size shouldBe 1
            }
        }
    }
})
