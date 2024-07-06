package com.groovyarea.assignment.datatransfer.common.exception

import com.groovyarea.assignment.datatransfer.common.model.MetaCode

class UnprocessableException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.UNPROCESSABLE_ENTITY,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
