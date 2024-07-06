package com.groovyarea.assignment.cashnote.infrastructure.http

import com.groovyarea.assignment.cashnote.common.logback.HttpLog
import feign.RequestInterceptor
import feign.RequestTemplate
import org.slf4j.MDC

class TraceHeaderInterceptor : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        template.header(HttpLog.X_REQUEST_ID, MDC.get(HttpLog.REQUEST_ID))
    }
}
