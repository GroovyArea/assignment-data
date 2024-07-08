package com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository

import com.groovyarea.assignment.cashnote.common.entity.ID
import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CardTransactionRepository : JpaRepository<CardTransaction, ID> {

    fun findAllByRegistrationNumberAndCreatedAtBetween(
        registrationNumber: String,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
        pageable: Pageable,
    ): Page<CardTransaction>
}
