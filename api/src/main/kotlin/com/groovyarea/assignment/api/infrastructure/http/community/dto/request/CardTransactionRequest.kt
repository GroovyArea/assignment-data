package com.groovyarea.assignment.api.infrastructure.http.community.dto.request

import java.time.LocalDate
import java.time.LocalTime

data class CardTransactionRequest(
    val cardTransactionNumber: Int,
    val status: String,
    val transactionDate: LocalDate,
    val transactionTime: LocalTime,
    val issuerName: String,
    val affiliateIssuerName: String,
    val cardNumber: String,
    val authNumber: String,
    val authAmount: String,
    val quota: String,
)
