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

    @KafkaListener(
        topics = ["\${kafka-topic.card-payment-transaction}"],
        clientIdPrefix = "receive-card-transactions-consumer"
    )
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
                logger.error("공동체 매출 데이터 수신 중 예외 발생 | 예외 메시지 : ${it.message}", it)
            }
    }
}
