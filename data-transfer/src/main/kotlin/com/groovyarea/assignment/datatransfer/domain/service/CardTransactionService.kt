package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction
import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.CardTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CardTransactionService(
    private val cardTransactionRepository: CardTransactionRepository,
) {

    @Transactional(readOnly = true)
    fun getDayBeforeDatetimeCardTransactions(
        registrationNumber: String,
        dayBeforeDatetime: LocalDateTime,
    ): List<CardTransaction> {
        val cardTransactions =
            cardTransactionRepository.findAllByRegistrationNumberAndDataTransferredIsFalseAndCreatedAtGreaterThanEqual(
                registrationNumber = registrationNumber,
                createdAt = dayBeforeDatetime
            )

        return cardTransactions
    }

    @Transactional
    fun dataTransferAll(
        cardTransactions: List<CardTransaction>
    ) {
        cardTransactions.forEach {
            it.transfer()
        }
        cardTransactionRepository.saveAll(cardTransactions)
    }
}
