package com.groovyarea.assignment.cashnote.infrastructure.http.community.client

import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.request.RegisterDataCommunicationRequest
import com.groovyarea.assignment.cashnote.infrastructure.http.community.dto.response.HasBusinessResponse
import com.groovyarea.assignment.cashnote.infrastructure.http.config.DefaultConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "community-client",
    configuration = [DefaultConfig::class]
)
interface CommunityClient {

    @GetMapping("/has-business")
    fun hasBusiness(
        @RequestParam registrationNumber: String,
    ): HasBusinessResponse

    @PostMapping("/register-data-communication")
    fun registerDataCommunication(
        @RequestBody request: RegisterDataCommunicationRequest,
    )
}
