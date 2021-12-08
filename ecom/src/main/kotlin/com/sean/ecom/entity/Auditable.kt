package com.sean.ecom.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditableListener::class)
abstract class Auditable {

    @Basic
    @CreatedBy
    var insertUser: String? = null

    @Basic
    var insertDate: Date? = null

    @Basic
    @LastModifiedBy
    var updateUser: String? = null

    @Basic
    var updateDate: Date? = null

    var void: Boolean = false
}