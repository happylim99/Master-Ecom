package com.sean.auth.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class Role {

    enum class RoleName {
        root, admin, superuser, user
    }

    constructor(name: String) {
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(unique = true)
    var name: String = RoleName.user.name

    @Column
    @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy = "roles", cascade = [CascadeType.DETACH])
    var users: Set<User> = setOf()
    override fun toString(): String {
        return "Role(id=$id, name='$name', users=$users)"
    }


}