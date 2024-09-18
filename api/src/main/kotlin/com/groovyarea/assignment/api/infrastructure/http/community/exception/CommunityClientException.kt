package com.groovyarea.assignment.api.infrastructure.http.community.exception

import com.groovyarea.assignment.api.common.exception.InvalidException

class CommunityClientException(
    override var message: String? = "공동체 API 호출에 실패했습니다."
) : InvalidException(
    message = message
)
