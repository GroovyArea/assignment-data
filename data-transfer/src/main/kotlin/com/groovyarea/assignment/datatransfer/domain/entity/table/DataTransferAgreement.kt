package com.groovyarea.assignment.datatransfer.domain.entity.table

import com.groovyarea.assignment.datatransfer.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "data_transfer_agreements")
class DataTransferAgreement(
    @Column(name = "registration_number")
    val registrationNumber: String,

    @Column(name = "data_transfer_agreed")
    val dataTransferAgreed: Boolean = true,

    @Column(name = "data_transfer_agreed_at")
    var dataTransferAgreedAt: LocalDateTime = LocalDateTime.now(),
) : EntityBase() {

    fun isNextDayOfAgreement(): Boolean {
        val agreedDate = this.dataTransferAgreedAt.toLocalDate()
        val today = LocalDate.now()

        return agreedDate < today
    }
}
