package com.groovyarea.assignment.datatransfer.external.mapper

import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction
import com.groovyarea.assignment.datatransfer.infrastructure.http.community.dto.request.CardTransactionRequest
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

    fun convertToRequest(
        cardTransaction: CardTransaction,
    ): CardTransactionRequest
}
