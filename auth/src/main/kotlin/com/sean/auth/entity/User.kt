package com.sean.auth.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class User: Auditable<String> {

    constructor()

    constructor(username: String, email: String, passwd: String) {
        this.username = username
        this.email = email
        this.password = passwd
    }

    constructor(
        id: Long,
        userId: String,
        name: String,
        username: String,
        email: String,
        emailVerificationToken: String,
        emailVerificationStatus: Boolean,
        password: String,
        roles: MutableSet<Role>
    ) : super() {
        this.id = id
        this.userId = userId
        this.name = name
        this.username = username
        this.email = email
        this.emailVerificationToken = emailVerificationToken
        this.emailVerificationStatus = emailVerificationStatus
        this.password = password
        this.roles = roles
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable=false)
    var userId = ""

    @Column(nullable=false, length=50)
    var name = ""

    @Column(nullable=false, length=30)
    var username = ""

    @Column(nullable=false, length=120, unique = true)
    var email = ""

    @Column
    var emailVerificationToken = ""

    @Column(nullable=false)
    var emailVerificationStatus = false

    @Column(nullable=false)
    @JsonIgnore
    var password = ""

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    private val addresses: MutableList<Address>? = null

    @Column
    @JsonIgnoreProperties("users")
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @JoinTable(name = "role_user", joinColumns =
        [JoinColumn(name = "user_id", nullable = false, updatable = false)],
        inverseJoinColumns =
        [JoinColumn(name = "role_id", nullable = false, updatable = false)])
    var roles: MutableSet<Role>  = mutableSetOf()
}