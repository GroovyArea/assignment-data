package com.groovyarea.assignment.datareceiver.common.exception

import com.groovyarea.assignment.datareceiver.common.model.MetaCode

class InvalidException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.INVALID_PARAMETER,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
