package com.groovyarea.assignment.datatransfer.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableJpaAuditing
@EnableScheduling
class AppConfig
