package com.groovyarea.assignment.cashnote.domain.entity.table

import com.groovyarea.assignment.cashnote.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "data_transfer_agreements")
class DataTransferAgreement(
    @Column(name = "registration_number")
    val registrationNumber: String,

    @Column(name = "is_data_transfer_agreed")
    val isDataTransferAgreed: Boolean,

    @Column(name = "data_transfer_agreed_at")
    var dataTransferAgreedAt: LocalDateTime,
) : EntityBase()
