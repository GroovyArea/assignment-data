package com.groovyarea.assignment.cashnote.application.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.groovyarea.assignment.cashnote.application.dto.AgreeConnectionCheckRequest
import com.groovyarea.assignment.cashnote.application.dto.AgreeConnectionCheckResponse
import com.groovyarea.assignment.cashnote.application.dto.AgreeDataTransferRequest
import com.groovyarea.assignment.cashnote.application.service.CashNoteApplicationService
import com.groovyarea.assignment.cashnote.common.dto.response.ResponseDTO
import com.groovyarea.assignment.cashnote.extension.RestdocBehaviorSpec
import com.groovyarea.assignment.cashnote.extension.andDocument
import com.groovyarea.assignment.cashnote.extension.boolean
import com.groovyarea.assignment.cashnote.extension.defineDocument
import com.groovyarea.assignment.cashnote.extension.desc
import com.groovyarea.assignment.cashnote.extension.field
import com.groovyarea.assignment.cashnote.extension.obj
import com.groovyarea.assignment.cashnote.extension.required
import com.groovyarea.assignment.cashnote.extension.string
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureRestDocs
@WebMvcTest(CashNoteController::class)
@ActiveProfiles("test")
class CashNoteControllerTest(
    @MockkBean private val appService: CashNoteApplicationService,
    @Autowired private val mockmvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
) : RestdocBehaviorSpec({

    given("간편 연결 확인 동의를 위해") {
        val request = AgreeConnectionCheckRequest(
            registrationNumber = "909-23-23421"
        )
        val response = AgreeConnectionCheckResponse(
            result = true
        )

        every {
            appService.agreeConnectionCheck(
                request = request
            )
        } returns response

        val expectedResponse = ResponseDTO.success(data = response)

        `when`("연결 확인 동의 API 요청을 하면") {
            val apiResponse = mockmvc.perform(
                RestDocumentationRequestBuilders.post(
                    "/api/v1/agree-connection-checks"
                ).content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )

            val expectedBody = objectMapper.writeValueAsString(expectedResponse)

            then("간편연결 확인 동의 가능에 대한 결과를 응답 받는다.") {
                apiResponse
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.content().json(expectedBody))
                    .andDocument(
                        defineDocument(
                            identifier = "Cash Note 간편 연결 확인 동의 API",
                            tag = "Cash Note",
                            requestSchema = "CashNoteAgreeConnectionChecksRequest",
                            responseSchema = "CashNoteAgreeConnectionChecksResponse",
                        ),
                        requestFields = arrayOf(
                            field("registrationNumber").string().desc("사업자 번호").required()
                        ),
                        responseFields = arrayOf(
                            field("meta").desc("Meta").obj(),
                            field("meta.code").desc("MetaCode").string(),
                            field("meta.type").desc("MetaCode name").string().optional(),
                            field("meta.message").desc("Error Message").string().optional(),
                            field("data").desc("result Data").required(),
                            field("data.result").boolean().desc("간편 연결 가능 여부").boolean()
                        )
                    )
            }
        }
    }

    given("데이터 제공 동의를 위해") {
        val request = AgreeDataTransferRequest(
            registrationNumber = "909-23-23421"
        )

        every {
            appService.agreeDataTransfer(request = request)
        } returns Unit

        val expectedResponse = ResponseDTO.success(data = null)

        `when`("데이터 제공 동의 API 요청을 하면") {
            val response = mockmvc.perform(
                RestDocumentationRequestBuilders.post(
                    "/api/v1/agree-data-transfer"
                ).content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )

            val expectedBody = objectMapper.writeValueAsString(expectedResponse)

            then("데이터 제공 동의가 완료 된다.") {
                response
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.content().json(expectedBody))
                    .andDocument(
                        defineDocument(
                            identifier = "Cash Note 데이터 제공 동의 API",
                            tag = "Cash Note",
                            requestSchema = "CashNoteAgreeDataTransferRequest",
                            responseSchema = "CashNoteAgreeDataTransferResponse",
                        ),
                        requestFields = arrayOf(
                            field("registrationNumber").string().desc("사업자 번호").required()
                        ),
                        responseFields = arrayOf(
                            field("meta").desc("Meta").obj(),
                            field("meta.code").desc("MetaCode").string(),
                            field("meta.type").desc("MetaCode name").string().optional(),
                            field("meta.message").desc("Error Message").string().optional(),
                            field("data").desc("result Data").optional(),
                        )
                    )
            }
        }
    }
})
