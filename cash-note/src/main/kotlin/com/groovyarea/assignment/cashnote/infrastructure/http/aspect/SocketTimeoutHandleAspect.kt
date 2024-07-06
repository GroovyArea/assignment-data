package com.groovyarea.assignment.cashnote.infrastructure.http.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import java.net.SocketTimeoutException
import kotlin.reflect.full.primaryConstructor

@Component
@Aspect
class SocketTimeoutHandleAspect {

    @Pointcut("@annotation(com.groovyarea.assignment.cashnote.infrastructure.http.aspect.SocketTimeoutHandle)")
    fun socketTimeoutHandle() = Unit

    @Around("socketTimeoutHandle()")
    fun annotatedSocketTimeoutHandle(
        joinPoint: ProceedingJoinPoint,
    ): Any = kotlin.runCatching {
        joinPoint.proceed()
    }.onFailure { ex ->
        if (ex.cause is SocketTimeoutException) {
            val specificSocketTimeoutException = generateSpecificSocketTimeoutException(
                joinPoint = joinPoint,
                errorMessage = ex.message
            )
            throw specificSocketTimeoutException
        }
    }.getOrThrow()

    private fun generateSpecificSocketTimeoutException(
        joinPoint: ProceedingJoinPoint,
        errorMessage: String?,
    ): Throwable {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        val errorClazz = method.getAnnotation(SocketTimeoutHandle::class.java).errorClazz
        val callableException = errorClazz.primaryConstructor!!.call(errorMessage)
        return callableException
    }
}
