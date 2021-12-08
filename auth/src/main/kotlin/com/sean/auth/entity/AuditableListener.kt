package com.sean.auth.entity

import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class AuditableListener {

    @PrePersist
    fun prePersist(obj: Auditable<*>) {
        obj.insertDate = Date()
        getCurrentUser()?.let { obj.insertUser = it }
    }

    @PreUpdate
    fun preUpdate(obj: Auditable<*>) {
        obj.updateDate = Date()
        getCurrentUser()?.let { obj.updateUser = it }
    }

    private fun getCurrentUser(): String? {
        return "auditable"
    }
}