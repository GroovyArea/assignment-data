package com.groovyarea.assignment.datareceiver.internal.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.groovyarea.assignment.datareceiver.common.logback.Log
import com.groovyarea.assignment.datareceiver.infrastructure.db.jpa.ReceiveCardTransactionRepository
import com.groovyarea.assignment.datareceiver.internal.kafka.dto.CardTransactionEvent
import com.groovyarea.assignment.datareceiver.internal.kafka.mapper.CardTransactionMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

/**
 * 공동체 수신 메시지 저장
 */
@Component
class ReceiveCardTransactionConsumer(
    private val objectMapper: ObjectMapper,
    private val receiveCardTransactionRepository: ReceiveCardTransactionRepository,
) {

    companion object : Log

    @KafkaListener(topics = ["transactions"], clientIdPrefix = "received-card-transactions-consumer")
    fun consumeCardTransaction(
        @Payload payload: String,
    ) {
        val result = kotlin.runCatching {
            objectMapper.readValue(
                payload,
                CardTransactionEvent::class.java
            )
        }

        result
            .onSuccess {
                val receiveCardTransaction = CardTransactionMapper.INSTANCE.convertToReceiveCardTransaction(
                    event = it
                )
                receiveCardTransactionRepository.save(receiveCardTransaction)
            }

        result
            .onFailure {
                logger.error(it.message, it)
            }
    }
}
