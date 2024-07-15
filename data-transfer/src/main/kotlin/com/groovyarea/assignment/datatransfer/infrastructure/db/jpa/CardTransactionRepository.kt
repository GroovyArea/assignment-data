package com.groovyarea.assignment.datatransfer.infrastructure.db.jpa

import com.groovyarea.assignment.datatransfer.common.entity.ID
import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface CardTransactionRepository : JpaRepository<CardTransaction, ID> {

    @Query(
        """
            select ct
            from CardTransaction ct
            where ct.registrationNumber = :registrationNumber
            and ct.dataTransferred = false
            and DATE(ct.createdAt) >= DATE(:createdAt)
        """
    )
    fun findAllByRegistrationNumberAndDataTransferredIsFalseAndCreatedAtGreaterThanEqual(
        registrationNumber: String,
        createdAt: LocalDateTime,
    ): List<CardTransaction>
}
