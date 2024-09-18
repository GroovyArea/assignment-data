package com.groovyarea.assignment.api.common.exception

import com.groovyarea.assignment.api.common.model.MetaCode

open class InternalServerException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.INTERNAL_SERVER_ERROR,
    message = message,
    data = data
)
