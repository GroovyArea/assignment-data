package com.groovyarea.assignment.cashnote.common.exception

import com.groovyarea.assignment.cashnote.common.model.MetaCode
import kotlin.RuntimeException

open class BaseHttpException(
    open var metaCode: MetaCode? = MetaCode.INTERNAL_SERVER_ERROR,
    override var message: String? = null,
    open val data: Any? = null
) : RuntimeException()
