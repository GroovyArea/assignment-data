package com.groovyarea.assignment.cashnote.domain.entity.table

import com.groovyarea.assignment.cashnote.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "connection_agreements")
class ConnectionAgreement(
    @Column(name = "registration_number")
    val registrationNumber: String,

    @Column(name = "is_connection_agreed")
    var isConnectionAgreed: Boolean = false,

    @Column(name = "connection_agreed_at")
    var connectionAgreedAt: LocalDateTime? = null,
) : EntityBase() {

    fun agree() {
        this.isConnectionAgreed = true
        this.connectionAgreedAt = LocalDateTime.now()
    }

    fun isNotAgreed(): Boolean {
        return !this.isConnectionAgreed
    }
}
