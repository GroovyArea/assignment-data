package com.groovyarea.assignment.cashnote.common.exception

import com.groovyarea.assignment.cashnote.common.model.MetaCode

open class RemoteTimeoutException(
    message: String? = null,
    data: Any? = null,
) : BaseHttpException(
    metaCode = MetaCode.REQUEST_TIMEOUT,
    message = message,
    data = data
) {

    constructor(metaCode: MetaCode, message: String) : this() {
        this.metaCode = metaCode
        this.message = message
    }
}
