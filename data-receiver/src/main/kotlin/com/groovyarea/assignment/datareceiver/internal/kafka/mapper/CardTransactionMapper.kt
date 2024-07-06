package com.groovyarea.assignment.datareceiver.internal.kafka.mapper

import com.groovyarea.assignment.datareceiver.domain.entity.table.ReceiveCardTransaction
import com.groovyarea.assignment.datareceiver.internal.kafka.dto.CardTransactionEvent
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(
    componentModel = ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
)
interface CardTransactionMapper {

    companion object {
        val INSTANCE: CardTransactionMapper = Mappers.getMapper(CardTransactionMapper::class.java)
    }

    fun convertToReceiveCardTransaction(
        event: CardTransactionEvent,
    ): ReceiveCardTransaction
}
