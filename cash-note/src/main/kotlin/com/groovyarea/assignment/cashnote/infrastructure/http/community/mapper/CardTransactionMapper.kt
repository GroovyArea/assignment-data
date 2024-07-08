package com.groovyarea.assignment.cashnote.infrastructure.http.community.mapper

import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.CardTransactionRequest
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

    fun convertToCardTransactionRequest(
        cardTransaction: CardTransaction,
    ): CardTransactionRequest
}
