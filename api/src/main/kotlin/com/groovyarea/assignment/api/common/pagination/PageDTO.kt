package com.groovyarea.assignment.api.common.pagination

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class PageDTO(
    val currentPageNumber: Int = 0,
    val currentPageSize: Int = 10,
    var sortOrder: Sort = Sort.unsorted(),
) : PageRequest(currentPageNumber, currentPageSize, sortOrder) {

    companion object {
        fun of(pageNumber: Int, pageSize: Int, sort: Sort? = null): PageDTO {
            return PageDTO(
                currentPageNumber = pageNumber,
                currentPageSize = pageSize,
                sortOrder = sort ?: Sort.unsorted(),
            )
        }
    }
}
