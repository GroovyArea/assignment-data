package com.groovyarea.assignment.api.common.exception

import com.groovyarea.assignment.api.common.model.MetaCode

class UnprocessableException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.UNPROCESSABLE_ENTITY,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
