package com.groovyarea.assignment.datatransfer.domain.service

import com.groovyarea.assignment.datatransfer.infrastructure.db.jpa.CardTransactionRepository
import org.springframework.stereotype.Service

@Service
class CardTransactionService(
    private val cardTransactionRepository: CardTransactionRepository
)
