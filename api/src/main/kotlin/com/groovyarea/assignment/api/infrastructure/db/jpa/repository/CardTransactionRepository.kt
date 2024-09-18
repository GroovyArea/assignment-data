package com.groovyarea.assignment.api.infrastructure.db.jpa.repository

import com.groovyarea.assignment.api.common.entity.ID
import com.groovyarea.assignment.api.domain.entity.table.CardTransaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.time.LocalDateTime

interface CardTransactionRepository : CoroutineCrudRepository<CardTransaction, ID> {

    suspend fun findAllByRegistrationNumberAndCreatedAtBetween(
        registrationNumber: String,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
        pageable: Pageable,
    ): Page<CardTransaction>
}
