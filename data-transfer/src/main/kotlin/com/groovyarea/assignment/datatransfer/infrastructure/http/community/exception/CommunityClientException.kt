package com.groovyarea.assignment.datatransfer.infrastructure.http.community.exception

import com.groovyarea.assignment.datatransfer.common.exception.InvalidException

class CommunityClientException(
    override var message: String? = "공동체 API 호출에 실패했습니다."
) : InvalidException(
    message = message
)
