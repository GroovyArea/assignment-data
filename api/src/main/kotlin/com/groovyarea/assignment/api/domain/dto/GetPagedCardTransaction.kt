package com.groovyarea.assignment.api.domain.dto

import com.groovyarea.assignment.api.domain.entity.table.CardTransaction

data class GetPagedCardTransaction(
    val nextPageNumber: Int,
    val contents: List<CardTransaction>,
)
