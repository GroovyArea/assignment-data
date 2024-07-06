package com.groovyarea.assignment.cashnote.domain.entity.table

import com.groovyarea.assignment.cashnote.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "data_provide_agreements")
class DataProvideAgreement(
    @Column(name = "registration_number")
    val registrationNumber: String,

    @Column(name = "agreed_at")
    var agreedAt: LocalDateTime,
) : EntityBase()
