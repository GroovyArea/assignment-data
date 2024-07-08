package com.groovyarea.assignment.cashnote.domain.dto

import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction

data class GetPagedCardTransaction(
    val nextPageNumber: Int,
    val contents: List<CardTransaction>,
)
