package com.groovyarea.assignment.datareceiver.infrastructure.db.jpa

import com.groovyarea.assignment.datareceiver.common.entity.ID
import com.groovyarea.assignment.datareceiver.domain.entity.table.ReceiveCardTransaction
import org.springframework.data.jpa.repository.JpaRepository

interface ReceiveCardTransactionRepository : JpaRepository<ReceiveCardTransaction, ID>
