package com.groovyarea.assignment.datareceiver.domain.entity.table

import com.groovyarea.assignment.datareceiver.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "receive_card_trasactions")
class ReceiveCardTransaction(

    @Column(name = "card_transaction_number")
    val cardTransactionNumber: Int,

    @Column(name = "status")
    val status: String,

    @Column(name = "transaction_date")
    val transactionDate: LocalDate,

    @Column(name = "transaction_time")
    val transactionTime: LocalTime,

    @Column(name = "issuer_name")
    val issuerName: String,

    @Column(name = "affiliate_issuer_name")
    val affiliateIssuerName: String,

    @Column(name = "card_number")
    val cardNumber: String,

    @Column(name = "auth_number")
    val authNumber: String,

    @Column(name = "auth_amount")
    val authAmount: String,

    @Column(name = "quota")
    val quota: String,
) : EntityBase()
