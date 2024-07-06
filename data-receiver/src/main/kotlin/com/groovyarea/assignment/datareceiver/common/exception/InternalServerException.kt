package com.groovyarea.assignment.datareceiver.common.exception

import com.groovyarea.assignment.datareceiver.common.model.MetaCode

open class InternalServerException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.INTERNAL_SERVER_ERROR,
    message = message,
    data = data
)
