package com.groovyarea.assignment.cashnote.domain.service

import com.groovyarea.assignment.cashnote.domain.entity.table.ConnectionAgreement
import com.groovyarea.assignment.cashnote.infrastructure.db.jpa.repository.ConnectionAgreementRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime

private val repository = mockk<ConnectionAgreementRepository>()

@InjectMockKs
private val connectionAgreementService = ConnectionAgreementService(
    connectionAgreementRepository = repository
)

class ConnectionAgreementServiceTest : BehaviorSpec({

    given("간편 연결 동의 후 데이터를 적재하기 위해") {
        val registrationNumber = "214-23-12345"
        val hasBusiness = true

        `when`("간편 연결 동의 레코드가 존재하지 않을 경우") {
            every {
                repository.findByRegistrationNumber(
                    registrationNumber = registrationNumber
                )
            } returns null

            val newConnectionAgreement = ConnectionAgreement(
                registrationNumber = registrationNumber,
                connectionAgreed = hasBusiness,
                connectionAgreedAt = LocalDateTime.now()
            )

            every {
                repository.save(any())
            } returns newConnectionAgreement

            connectionAgreementService.agree(
                registrationNumber = registrationNumber,
                hasBusiness = hasBusiness
            )

            then("간편 연결 동의 레코드가 적재 된다.") {
                verify(exactly = 1) {
                    repository.save(any())
                }
            }
        }

        `when`("간편 연결 동의 레코드가 이미 존재할 경우") {
            val connectionAgreement = ConnectionAgreement(
                registrationNumber = registrationNumber
            )

            every {
                repository.findByRegistrationNumber(
                    registrationNumber = registrationNumber
                )
            } returns connectionAgreement

            connectionAgreementService.agree(
                registrationNumber = registrationNumber,
                hasBusiness = hasBusiness
            )

            then("간편 연결 동의 Entity 의 동의 여부, 동의 날짜가 반영 된다.") {
                connectionAgreement.connectionAgreed shouldBe true
                connectionAgreement.connectionAgreedAt shouldNotBe null
            }
        }
    }
})
