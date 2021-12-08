package com.sean.auth.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditableListener::class)
abstract class Auditable<T> {

    @Basic
    @CreatedBy
    var insertUser: String? = null

    @Basic
    @LastModifiedBy
    var updateUser: String? = null

    @Basic
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
    var insertDate: Date? = null

    @Basic
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
    var updateDate: Date? = null

}