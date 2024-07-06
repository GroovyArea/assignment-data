package com.groovyarea.assignment.cashnote.application.web

import com.groovyarea.assignment.cashnote.application.dto.AgreeConnectionCheckRequest
import com.groovyarea.assignment.cashnote.application.dto.AgreeConnectionCheckResponse
import com.groovyarea.assignment.cashnote.application.service.CashNoteApplicationService
import com.groovyarea.assignment.cashnote.common.dto.response.ResponseDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CashNoteController(
    private val cashNoteApplicationService: CashNoteApplicationService,
) {

    /**
     * 간편연결 확인 동의 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/agree-connection-checks")
    fun agreementConnectionCheck(
        @Valid @RequestBody request: AgreeConnectionCheckRequest,
    ): ResponseDTO<AgreeConnectionCheckResponse> {
        val result = cashNoteApplicationService.agreeConnectionCheck(
            request = request
        )

        return ResponseDTO.success(data = result)
    }
}
