package com.groovyarea.assignment.datareceiver.common.exception

import com.groovyarea.assignment.datareceiver.common.model.MetaCode

class NotFoundException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.NOT_FOUND,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
