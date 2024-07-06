package com.groovyarea.assignment.datatransfer.external.scheduler

import com.groovyarea.assignment.datatransfer.domain.service.CardTransactionQueryService
import com.groovyarea.assignment.datatransfer.domain.service.DataTransferAgreementQueryService
import com.groovyarea.assignment.datatransfer.external.mapper.CardTransactionMapper
import com.groovyarea.assignment.datatransfer.external.service.CommunityService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 매일 데이터 전송 동의 완료 된 대상 한정, 전일 데이터 공동체 전송
 */
@Component
class DataTransferScheduler(
    private val dataTransferAgreementQueryService: DataTransferAgreementQueryService,
    private val cardTransactionQueryService: CardTransactionQueryService,
    private val communityService: CommunityService,
) {

    companion object {
        private const val CARD_TRANSACTION_CHUNK_SIZE = 10
    }

    @Scheduled(cron = "0 8 * * * ")
    fun sendData() {
        var currentDataTransferAgreementPageNumber = 0
        var transferAbleFlag = true
        val dayBeforeDatetime = LocalDateTime.now().minusDays(1)

        while (transferAbleFlag) {
            val pagedDataTransferAgreements =
                dataTransferAgreementQueryService.getPagedDataTransferAgreementsOfAgreed(
                    currentPageNumber = currentDataTransferAgreementPageNumber
                )

            if (pagedDataTransferAgreements.contents.isEmpty()) {
                transferAbleFlag = false
            }

            currentDataTransferAgreementPageNumber = pagedDataTransferAgreements.nextPageNumber

            pagedDataTransferAgreements.contents.forEach { dataTransferAgreement ->
                val registrationNumber = dataTransferAgreement.registrationNumber

                val yesterdayCardTransactions = cardTransactionQueryService.getDayBeforeDatetimeCardTransactions(
                    registrationNumber = registrationNumber,
                    dayBeforeDatetime = dayBeforeDatetime
                )

                yesterdayCardTransactions.chunked(CARD_TRANSACTION_CHUNK_SIZE).forEach {
                    val cardTransactionRequests = it.map { cardTransaction ->
                        CardTransactionMapper.INSTANCE.convertToRequest(
                            cardTransaction = cardTransaction
                        )
                    }
                    communityService.sendTransaction(
                        cardTransactionRequests = cardTransactionRequests
                    )
                }
            }
        }
    }
}
