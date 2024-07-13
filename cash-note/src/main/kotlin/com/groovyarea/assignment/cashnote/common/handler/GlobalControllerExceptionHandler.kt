package com.groovyarea.assignment.cashnote.common.handler

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.groovyarea.assignment.cashnote.common.dto.response.ResponseDTO
import com.groovyarea.assignment.cashnote.common.dto.response.ResponseDTO.Meta
import com.groovyarea.assignment.cashnote.common.exception.BaseHttpException
import com.groovyarea.assignment.cashnote.common.exception.InternalServerException
import com.groovyarea.assignment.cashnote.common.exception.InvalidException
import com.groovyarea.assignment.cashnote.common.exception.NotFoundException
import com.groovyarea.assignment.cashnote.common.exception.RemoteCallException
import com.groovyarea.assignment.cashnote.common.exception.RemoteTimeoutException
import com.groovyarea.assignment.cashnote.common.exception.UnprocessableException
import com.groovyarea.assignment.cashnote.common.logback.Log
import com.groovyarea.assignment.cashnote.common.model.MetaCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
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
    fun handleBaseHttpException(error: BaseHttpException):
        ResponseEntity<ResponseDTO<Any>> {
        val status = when (error) {
            is InvalidException -> HttpStatus.BAD_REQUEST
            is NotFoundException -> HttpStatus.NOT_FOUND
            is InternalServerException -> HttpStatus.INTERNAL_SERVER_ERROR
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        return createErrorResponse(statusCode = status, message = error.message, data = error.data)
    }

    @ExceptionHandler(
        value = [
            (Exception::class)
        ]
    )
    fun handleUnhandledException(error: Exception):
        ResponseEntity<ResponseDTO<Any>> {
        error.printStackTrace()
        return createErrorResponse(statusCode = HttpStatus.INTERNAL_SERVER_ERROR, message = error.message)
    }

    @ExceptionHandler(
        value = [
            (NotImplementedError::class)
        ]
    )
    fun handleNotImplementedError(error: NotImplementedError):
        ResponseEntity<ResponseDTO<Any>> {
        return createErrorResponse(statusCode = HttpStatus.NOT_IMPLEMENTED, message = error.message)
    }

    @ExceptionHandler(
        value = [
            (RemoteCallException::class)
        ]
    )
    fun handleRemoteCallException(error: RemoteCallException):
        ResponseEntity<ResponseDTO<Any>> {
        val status = error.remoteStatus

        return createErrorResponse(statusCode = status, message = error.message)
    }

    @ExceptionHandler(
        value = [
            (RemoteTimeoutException::class)
        ]
    )
    fun handleRemoteTimeoutException(error: RemoteTimeoutException):
        ResponseEntity<ResponseDTO<Any>> {
        val status = HttpStatus.REQUEST_TIMEOUT

        return createErrorResponse(statusCode = status, message = error.message)
    }

    @ExceptionHandler(
        value = [
            (UnprocessableException::class)
        ]
    )
    fun handleUnprocessableError(error: UnprocessableException):
        ResponseEntity<ResponseDTO<Any>> {
        return createErrorResponse(statusCode = HttpStatus.UNPROCESSABLE_ENTITY, message = error.message)
    }

    @ExceptionHandler(value = [(BindException::class)])
    fun handleValidationError(error: BindException): ResponseEntity<ResponseDTO<Any>> {
        val errorMessage = error.bindingResult.fieldErrors.map { it.defaultMessage }
        return createErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST,
            message = errorMessage.joinToString()
        )
    }

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun handleHttpMessageNotReadableError(error: HttpMessageNotReadableException): ResponseEntity<ResponseDTO<Any>> {
        val errorMessage = when (val caused = error.cause) {
            is MismatchedInputException -> { "${caused.targetType.name} 필드가 누락 되었습니다." }
            else -> error.message
        }
        return createErrorResponse(statusCode = HttpStatus.BAD_REQUEST, message = errorMessage)
    }

    private fun createErrorResponse(statusCode: HttpStatus, message: String? = null, data: Any? = null):
        ResponseEntity<ResponseDTO<Any>> {
        val dtoMetaCode = MetaCode.valueFrom(statusCode)

        val dto = ResponseDTO(
            meta = Meta(
                code = dtoMetaCode,
                type = dtoMetaCode.name.lowercase(),
                message = message
            ),
            data = data
        )

        return ResponseEntity(dto, statusCode)
    }
}
