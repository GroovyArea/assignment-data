package com.groovyarea.assignment.datatransfer.infrastructure.db.jpa

import com.groovyarea.assignment.datatransfer.common.entity.ID
import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CardTransactionRepository : JpaRepository<CardTransaction, ID> {

    fun findAllByRegistrationNumberAndCreatedAtGreaterThanEqual(
        registrationNumber: String,
        createdAt: LocalDateTime,
    ): List<CardTransaction>
}
