package com.groovyarea.assignment.datatransfer.common.exception

import com.groovyarea.assignment.datatransfer.common.model.MetaCode

open class InternalServerException(message: String? = null, data: Any? = null) : BaseHttpException(
    metaCode = MetaCode.INTERNAL_SERVER_ERROR,
    message = message,
    data = data
)
