package com.groovyarea.assignment.api.stub

import com.groovyarea.assignment.api.domain.entity.table.CardTransaction
import java.time.LocalDate
import java.time.LocalTime

internal fun dummyCardTransaction(): CardTransaction {
    return CardTransaction(
        registrationNumber = "214-23-12345",
        cardTransactionNumber = 1,
        status = "승인",
        transactionDate = LocalDate.now(),
        transactionTime = LocalTime.now(),
        issuerName = "국민카드",
        affiliateIssuerName = "",
        cardNumber = "2342-2342-****-**",
        authNumber = "10395736",
        authAmount = "10000.00",
        quota = "일시불"
    )
}
