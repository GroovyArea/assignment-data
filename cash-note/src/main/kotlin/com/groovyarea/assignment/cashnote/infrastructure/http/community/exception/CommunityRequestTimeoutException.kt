package com.groovyarea.assignment.cashnote.infrastructure.http.community.exception

import com.groovyarea.assignment.cashnote.common.exception.RemoteTimeoutException

class CommunityRequestTimeoutException(
    override var message: String?,
) : RemoteTimeoutException(message = message)
