package com.groovyarea.assignment.cashnote.domain.service

import com.groovyarea.assignment.cashnote.common.pagination.PageDTO
import com.groovyarea.assignment.cashnote.domain.dto.GetPagedCardTransaction
import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction
import com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository.CardTransactionRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CardTransactionService(
    private val cardTransactionRepository: CardTransactionRepository,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }

    suspend fun getCardTransactionsBetween(
        currentPageNumber: Int,
        registrationNumber: String,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): GetPagedCardTransaction {
        val pageDTO = PageDTO(
            currentPageNumber = currentPageNumber,
            currentPageSize = DEFAULT_PAGE_SIZE
        )
        val pagedCardTransactions = cardTransactionRepository.findAllByRegistrationNumberAndCreatedAtBetween(
            registrationNumber = registrationNumber,
            startDateTime = startDate,
            endDateTime = endDate,
            pageable = pageDTO
        )

        val nextPageNumber = pagedCardTransactions.pageable.next().pageNumber
        val contents = pagedCardTransactions.content

        return GetPagedCardTransaction(
            nextPageNumber = nextPageNumber,
            contents = contents
        )
    }

    suspend fun dataTransferAll(
        cardTransactions: List<CardTransaction>
    ) {
        cardTransactions.forEach {
            it.transfer()
        }
        cardTransactionRepository.saveAll(cardTransactions)
    }
}
