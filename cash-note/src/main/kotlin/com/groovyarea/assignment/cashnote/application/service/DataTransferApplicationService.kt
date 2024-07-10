package com.groovyarea.assignment.cashnote.application.service

import com.groovyarea.assignment.cashnote.application.service.community.CommunityService
import com.groovyarea.assignment.cashnote.common.logback.Log
import com.groovyarea.assignment.cashnote.domain.entity.table.CardTransaction
import com.groovyarea.assignment.cashnote.domain.service.CardTransactionQueryService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class DataTransferApplicationService(
    private val communityService: CommunityService,
    private val cardTransactionQueryService: CardTransactionQueryService,
) {

    companion object : Log {
        private const val AGO_SIX_MONTHS: Long = 6
        private const val LOOP_TIMEOUT_MINUTE: Long = 10
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        logger.error("과거 6개월 카드 데이터 송신 중 예외 발생", exception)
    }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO + coroutineExceptionHandler)

    fun transferFirstData(
        registrationNumber: String,
    ) = coroutineScope.launch {
        var currentCardTransactionPageNumber = 0
        var transactionAbleFlag = true
        val endDate = LocalDateTime.now()
        val startDate = endDate.minus(AGO_SIX_MONTHS, ChronoUnit.MONTHS)

        val loopStartTime = System.currentTimeMillis()
        val loopTimeout = Duration.ofMinutes(LOOP_TIMEOUT_MINUTE).toMillis()

        val result = runCatching {
            while (transactionAbleFlag && (System.currentTimeMillis() - loopStartTime < loopTimeout)) {
                val pagedCardTransactions = withContext(Dispatchers.IO) {
                    getPagedCardTransaction(
                        currentCardTransactionPageNumber = currentCardTransactionPageNumber,
                        startDate = startDate,
                        endDate = endDate,
                        registrationNumber = registrationNumber
                    )
                }

                if (pagedCardTransactions.contents.isEmpty()) {
                    transactionAbleFlag = false
                } else {
                    currentCardTransactionPageNumber = pagedCardTransactions.nextPageNumber

                    val cardTransactions = pagedCardTransactions.contents

                    withContext(Dispatchers.IO) {
                        sendData(
                            cardTransactions = cardTransactions
                        )
                    }

                    val lastCardTransactionNumber = cardTransactions.last().cardTransactionNumber
                    logger.info(
                        "과거 6개월 카드 데이터 송신 성공 | 마지막 송신 카드 번호 : $lastCardTransactionNumber | 사업자 번호 : $registrationNumber"
                    )
                }
            }
        }

        result
            .onFailure { e ->
                logger.error("카드 데이터 전송 중 오류 발생 | 사업자 번호 : $registrationNumber", e)
                coroutineScope.cancel("데이터 송신 종료")
            }
    }

    private suspend fun getPagedCardTransaction(
        currentCardTransactionPageNumber: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        registrationNumber: String,
    ) = cardTransactionQueryService.getCardTransactionsBetween(
        currentPageNumber = currentCardTransactionPageNumber,
        startDate = startDate,
        endDate = endDate,
        registrationNumber = registrationNumber
    )

    private suspend fun sendData(
        cardTransactions: List<CardTransaction>,
    ) = communityService.sendCardTransactions(
        cardTransactions = cardTransactions
    )
}
