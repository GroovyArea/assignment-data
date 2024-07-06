package com.groovyarea.assignment.datatransfer.infrastructure.db.jpa

import com.groovyarea.assignment.datatransfer.common.entity.ID
import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CardTransactionRepository : JpaRepository<CardTransaction, ID> {

    fun findAllByRegistrationNumber(
        registrationNumber: String,
        pageable: Pageable,
    ): Page<CardTransaction>

    fun findAllByRegistrationNumberAndCreatedAtGreaterThanEqual(
        registrationNumber: String,
        createdAt: LocalDateTime,
    ): List<CardTransaction>
}
