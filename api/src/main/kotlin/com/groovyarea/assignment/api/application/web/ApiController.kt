package com.groovyarea.assignment.api.application.web

import com.groovyarea.assignment.api.application.dto.AgreeConnectionCheckRequest
import com.groovyarea.assignment.api.application.dto.AgreeConnectionCheckResponse
import com.groovyarea.assignment.api.application.dto.AgreeDataTransferRequest
import com.groovyarea.assignment.api.application.service.ApplicationService
import com.groovyarea.assignment.api.common.dto.response.ResponseDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(
    private val applicationService: ApplicationService,
) {

    /**
     * 간편연결 확인 동의 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/agree-connection-checks")
    fun agreementConnectionCheck(
        @Valid @RequestBody request: AgreeConnectionCheckRequest,
    ): ResponseDTO<AgreeConnectionCheckResponse> {
        val result = applicationService.agreeConnectionCheck(
            request = request
        )

        return ResponseDTO.success(data = result)
    }

    /**
     * 데이터 제공 동의 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/agree-data-transfer")
    fun agreementDataTransfer(
        @Valid @RequestBody request: AgreeDataTransferRequest,
    ): ResponseDTO<Unit> {
        applicationService.agreeDataTransfer(
            request = request
        )

        return ResponseDTO.success()
    }
}
