package com.groovyarea.assignment.datareceiver.common.handler

import com.groovyarea.assignment.datareceiver.common.exception.BaseHttpException
import com.groovyarea.assignment.datareceiver.common.exception.InternalServerException
import com.groovyarea.assignment.datareceiver.common.exception.InvalidException
import com.groovyarea.assignment.datareceiver.common.exception.NotFoundException
import com.groovyarea.assignment.datareceiver.common.exception.RemoteTimeoutException
import com.groovyarea.assignment.datareceiver.common.logback.Log
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class GlobalControllerExceptionHandler {

    companion object : Log

    @ExceptionHandler(
        value = [
            (InvalidException::class),
            (NotFoundException::class),
            (InternalServerException::class),
        ]
    )
    @ResponseBody
    fun handleBaseHttpException(error: BaseHttpException) {
        logger.error(error.message, error)
    }

    @ExceptionHandler(
        value = [
            (Exception::class)
        ]
    )
    fun handleUnhandledException(error: Exception) {
        logger.error(error.message, error)
    }

    @ExceptionHandler(
        value = [
            (NotImplementedError::class)
        ]
    )
    fun handleNotImplementedError(error: NotImplementedError) {
        logger.error(error.message, error)
    }

    @ExceptionHandler(
        value = [
            (RemoteTimeoutException::class)
        ]
    )
    fun handleRemoteTimeoutException(error: RemoteTimeoutException) {
        logger.error(error.message, error)
    }
}
