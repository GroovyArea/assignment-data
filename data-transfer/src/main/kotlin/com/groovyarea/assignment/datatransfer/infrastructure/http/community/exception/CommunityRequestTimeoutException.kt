package com.groovyarea.assignment.datatransfer.infrastructure.http.community.exception

import com.groovyarea.assignment.datatransfer.common.exception.RemoteTimeoutException

class CommunityRequestTimeoutException(
    override var message: String?,
) : RemoteTimeoutException(message = message)
