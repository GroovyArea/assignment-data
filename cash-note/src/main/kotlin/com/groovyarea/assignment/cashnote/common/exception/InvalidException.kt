package com.groovyarea.assignment.cashnote.common.exception

import com.groovyarea.assignment.cashnote.common.model.MetaCode

open class InvalidException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.INVALID_PARAMETER,
    message = message,
    data = data
) {
    constructor(exception: Exception) : this() {
        this.message = exception.message
    }
}
