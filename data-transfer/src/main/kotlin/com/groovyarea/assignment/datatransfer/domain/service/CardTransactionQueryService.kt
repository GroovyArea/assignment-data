package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction
import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.CardTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CardTransactionQueryService(
    private val cardTransactionRepository: CardTransactionRepository,
) {

    @Transactional(readOnly = true)
    fun getDayBeforeDatetimeCardTransactions(
        registrationNumber: String,
        dayBeforeDatetime: LocalDateTime,
    ): List<CardTransaction> {
        val cardTransactions =
            cardTransactionRepository.findAllByRegistrationNumberAndCreatedAtGreaterThanEqual(
                registrationNumber = registrationNumber,
                createdAt = dayBeforeDatetime
            )

        return cardTransactions
    }
}
