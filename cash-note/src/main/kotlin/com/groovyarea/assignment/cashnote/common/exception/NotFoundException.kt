package com.groovyarea.assignment.cashnote.common.exception

import com.groovyarea.assignment.cashnote.common.model.MetaCode

class NotFoundException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.NOT_FOUND,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
