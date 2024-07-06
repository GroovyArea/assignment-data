package com.groovyarea.assignment.datatransfer.common.pagination

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import kotlin.math.max

class PageDTO(
    val currentPageNumber: Int = 0,
    val currentPageSize: Int = 10,
    var sortOrder: Sort = Sort.unsorted(),
) : PageRequest(currentPageNumber, currentPageSize, sortOrder) {

    companion object {
        fun of(pageNumber: Int, pageSize: Int, sort: Sort? = null): PageDTO {
            return PageDTO(
                currentPageNumber = convertToServerPage(pageNumber),
                currentPageSize = pageSize,
                sortOrder = sort ?: Sort.unsorted(),
            )
        }

        private fun convertToServerPage(requestPageNumber: Int): Int {
            return max(requestPageNumber - 1, 0)
        }
    }

    fun updateSortOrder(newSort: Sort) {
        this.sortOrder = newSort
    }
}
