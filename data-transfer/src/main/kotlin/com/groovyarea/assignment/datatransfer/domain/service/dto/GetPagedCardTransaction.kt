package com.groovyarea.assignment.datatransfer.domain.service.dto

import com.groovyarea.assignment.datatransfer.domain.entity.table.CardTransaction

data class GetPagedCardTransaction(
    val currentPageNumber: Int,
    val contents: List<CardTransaction>,
)
