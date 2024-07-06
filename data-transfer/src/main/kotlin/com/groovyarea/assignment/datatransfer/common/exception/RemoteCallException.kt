package com.groovyarea.assignment.datatransfer.common.exception

import com.groovyarea.assignment.datatransfer.common.model.MetaCode
import org.springframework.http.HttpStatus

open class RemoteCallException(
    val remoteStatus: HttpStatus,
    override var metaCode: MetaCode? = MetaCode.INTERNAL_SERVER_ERROR,
    remoteMessage: String? = null
) : BaseHttpException(
    metaCode = metaCode,
    message = "$remoteStatus : $remoteMessage"
)
