package com.groovyarea.assignment.datatransfer.common.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.Objects

typealias ID = Long

@MappedSuperclass
abstract class EntityBase {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: ID? = null

    @CreationTimestamp
    @CreatedDate
    open var createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @LastModifiedDate
    open var updatedAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Any? {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as EntityBase).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)
}
