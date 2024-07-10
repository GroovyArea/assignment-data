package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.CardTransactionRepository
import com.groovyarea.assignment.datatransfer.stub.dummyCardTransaction
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import java.time.LocalDateTime

private val repository = mockk<CardTransactionRepository>()

@InjectMockKs
private val cardTransactionQueryService = CardTransactionQueryService(
    cardTransactionRepository = repository
)

class CardTransactionQueryServiceTest : BehaviorSpec({

    given("어제 카드 데이터를 조회하기 위해") {
        val registrationNumber = "214-23-12345"
        val dayBeforeDatetime = LocalDateTime.now().minusDays(1)

        every {
            repository.findAllByRegistrationNumberAndCreatedAtGreaterThanEqual(
                registrationNumber = registrationNumber,
                createdAt = dayBeforeDatetime
            )
        } returns listOf(
            dummyCardTransaction()
        )

        `when`("함수를 호출하면") {
            val dayBeforeDatetimeCardTransactions = cardTransactionQueryService.getDayBeforeDatetimeCardTransactions(
                registrationNumber = registrationNumber,
                dayBeforeDatetime = dayBeforeDatetime
            )

            then("쿼리 결과를 반환한다.") {
                dayBeforeDatetimeCardTransactions.size shouldBe 1
            }
        }
    }
})
