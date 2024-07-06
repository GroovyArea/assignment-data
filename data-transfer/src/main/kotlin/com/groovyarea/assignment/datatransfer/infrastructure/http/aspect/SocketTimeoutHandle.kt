package com.groovyarea.assignment.datatransfer.infrastructure.http.aspect

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SocketTimeoutHandle(

    val errorClazz: KClass<out Throwable>,
)
