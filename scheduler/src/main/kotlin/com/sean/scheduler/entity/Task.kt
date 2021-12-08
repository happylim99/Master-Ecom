package com.sean.scheduler.entity

import org.hibernate.annotations.*
import javax.persistence.*
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE task SET void = true WHERE id=? and version=?")
@FilterDefs(
    FilterDef(name = "void", parameters = [ParamDef(name = "isVoid", type = "boolean")])
)
@Filter(name = "void", condition = "void = :isVoid")
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 35, nullable = false)
    lateinit var uid: String

    @Column(length = 50, nullable = false)
    lateinit var cronJob: String

    @Column(length = 50, nullable = false)
    lateinit var beanName: String

    @Column(length = 1, nullable = false)
    var schedule: Boolean = false

    @Column(length = 30)
    var groupName: String? = null

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    var version: Long = 0L

    @Column(length = 1, nullable = false)
    var void: Boolean = false
}