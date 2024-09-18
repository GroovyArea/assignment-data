package com.groovyarea.assignment.api.common.exception

import com.groovyarea.assignment.api.common.model.MetaCode

open class InvalidException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.INVALID_PARAMETER,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
