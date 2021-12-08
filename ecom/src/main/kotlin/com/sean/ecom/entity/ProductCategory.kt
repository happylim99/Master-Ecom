package com.sean.ecom.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import org.hibernate.annotations.SQLDelete
import javax.persistence.*

@Entity
@SQLDelete(sql = "UPDATE product_category SET void = true WHERE id=?")
@FilterDef(name = "void", parameters = [ParamDef(name = "isVoid", type = "boolean")])
@Filter(name = "void", condition = "void = :isVoid")
class ProductCategory: Auditable {

    constructor()

    constructor(name: String) {
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 35, nullable = false)
    lateinit var uid: String

    @Column(unique = true, length = 20, nullable = false)
    lateinit var name: String

    @Column(length = 100)
    var description: String? = null

    @JsonIgnoreProperties("category")
    @ManyToMany(mappedBy = "category", cascade = [ CascadeType.DETACH ])
    var masterProducts: MutableSet<MasterProduct>? = null

}