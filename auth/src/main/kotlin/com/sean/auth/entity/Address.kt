package com.sean.auth.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class Address {

    constructor()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length=30, nullable=false)
    var addressId: String = ""

    @Column(length=15, nullable=false)
    var city: String = ""

    @Column(length=15, nullable=false)
    var country: String = ""

    @Column(length=100, nullable=false)
    var streetName: String = ""

    @Column(length=7, nullable=false)
    var postalCode: String = ""

    @Column(length=10, nullable=false)
    var type: String = ""

    @JsonIgnoreProperties("addresses")
    @ManyToOne
    @JoinColumn(name="user_id")
    var user: User? = null
}