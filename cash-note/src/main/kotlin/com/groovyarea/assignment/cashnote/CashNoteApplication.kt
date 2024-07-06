package com.groovyarea.assignment.cashnote

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CashNoteApplication

fun main(args: Array<String>) {
    runApplication<CashNoteApplication>(*args)
}
