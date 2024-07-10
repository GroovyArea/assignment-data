package com.groovyarea.assignment.datatransfer.external.scheduler

import com.groovyarea.assignment.datatransfer.common.logback.Log
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

    companion object : Log {
        private const val CARD_TRANSACTION_CHUNK_SIZE = 10
    }

    @Scheduled(cron = "0 0 8 * * *")
    fun sendData() {
        var currentDataTransferAgreementPageNumber = 0
        var transferAbleFlag = true
        val dayBeforeDatetime = LocalDateTime.now().minusDays(1)

        while (transferAbleFlag) {
            val pagedDataTransferAgreementsResult = runCatching {
                dataTransferAgreementQueryService.getPagedDataTransferAgreementsOfAgreed(
                    currentPageNumber = currentDataTransferAgreementPageNumber
                )
            }

            pagedDataTransferAgreementsResult.onSuccess { pagedDataTransferAgreements ->
                if (pagedDataTransferAgreements.contents.isEmpty()) {
                    transferAbleFlag = false
                } else {
                    currentDataTransferAgreementPageNumber = pagedDataTransferAgreements.nextPageNumber

                    pagedDataTransferAgreements.contents.forEach { dataTransferAgreement ->
                        val registrationNumber = dataTransferAgreement.registrationNumber

                        val yesterdayCardTransactionsResult = runCatching {
                            cardTransactionQueryService.getDayBeforeDatetimeCardTransactions(
                                registrationNumber = registrationNumber,
                                dayBeforeDatetime = dayBeforeDatetime
                            )
                        }

                        yesterdayCardTransactionsResult.onSuccess { yesterdayCardTransactions ->
                            yesterdayCardTransactions.chunked(CARD_TRANSACTION_CHUNK_SIZE).forEach { chunk ->
                                val cardTransactionRequests = chunk.map { cardTransaction ->
                                    CardTransactionMapper.INSTANCE.convertToRequest(
                                        cardTransaction = cardTransaction
                                    )
                                }
                                val sendTransactionResult = runCatching {
                                    communityService.sendTransaction(
                                        cardTransactionRequests = cardTransactionRequests
                                    )
                                }

                                sendTransactionResult.onFailure { e ->
                                    logger.error("사업자 번호 $registrationNumber 의 매출 데이터 전송 중 에러 발생", e)
                                }
                            }
                        }

                        yesterdayCardTransactionsResult.onFailure { e ->
                            logger.error("사업자 번호 $registrationNumber 의 매출 데이터 조회 중 에러 발생", e)
                        }
                    }
                }
            }

            pagedDataTransferAgreementsResult.onFailure { e ->
                logger.error("페이지 번호 $currentDataTransferAgreementPageNumber 의 데이터 전송 동의 조회 중 에러 발생", e)
                transferAbleFlag = false
            }
        }
    }
}
