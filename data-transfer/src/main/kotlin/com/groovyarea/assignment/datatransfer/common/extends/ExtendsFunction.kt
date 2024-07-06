package com.groovyarea.assignment.datatransfer.common.extends

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

const val MAX_DATE_RANGE_DAYS = 30
const val FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val FORMAT_KST_TO_UTC = "yyyy-MM-dd 15:00:00"
const val DIFFICULT_TIME = 9L

fun LocalDateTime.toMidnight(): LocalDateTime {
    return LocalDateTime
        .parse(
            this.format(DateTimeFormatter.ofPattern(FORMAT_KST_TO_UTC)),
            DateTimeFormatter.ofPattern(FORMAT_DATE_TIME)
        )
}

private const val ZERO_TIME = 0
private const val LAST_HOURS = 23
private const val LAST_MINUTE = 59
private const val LAST_SECOND = 59
private const val ONE_TIME = 1L

internal fun LocalDateTime.firstDayAndTimeOfMonthMinusHoursAndMonths(hours: Long, months: Long) = withHour(ZERO_TIME)
    .withMinute(ZERO_TIME)
    .withSecond(ZERO_TIME)
    .withNano(ZERO_TIME)
    .with(TemporalAdjusters.firstDayOfMonth())
    .minusMonths(months)
    .minusHours(hours)

internal fun LocalDateTime.lastDayAndTimeOfMonthMinusHoursAndMonths(hours: Long, months: Long) = withHour(LAST_HOURS)
    .withMinute(LAST_MINUTE)
    .withSecond(LAST_SECOND)
    .withNano(ZERO_TIME)
    .with(TemporalAdjusters.lastDayOfMonth())
    .minusMonths(months)
    .minusHours(hours)
    .plusSeconds(ONE_TIME)

fun LocalDateTime.toKST(): LocalDateTime {
    return this.plusHours(DIFFICULT_TIME)
}

fun LocalDateTime.toUTC(): LocalDateTime {
    return this.minusHours(DIFFICULT_TIME)
}

fun String.toSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase(Locale.getDefault())
}
