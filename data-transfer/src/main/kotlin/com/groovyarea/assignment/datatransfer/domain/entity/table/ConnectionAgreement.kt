package com.groovyarea.assignment.datatransfer.domain.entity.table

import com.groovyarea.assignment.datatransfer.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "connection_agreements")
class ConnectionAgreement(
    @Column(name = "registration_number")
    val registrationNumber: String,

    @Column(name = "connection_agreed")
    var connectionAgreed: Boolean = false,

    @Column(name = "connection_agreed_at")
    var connectionAgreedAt: LocalDateTime? = null,
) : EntityBase() {

    fun isNotAgreed(): Boolean {
        return !this.connectionAgreed
    }
}
