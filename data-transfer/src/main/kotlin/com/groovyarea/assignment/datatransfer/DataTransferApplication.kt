package com.groovyarea.assignment.datatransfer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class DataTransferApplication

fun main(args: Array<String>) {
    runApplication<DataTransferApplication>(*args)
}
