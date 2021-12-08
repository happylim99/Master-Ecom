package com.sean.ecom.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.*
import javax.persistence.*
import javax.persistence.CascadeType
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE master_product SET void = true WHERE id=?")
@FilterDefs(
    FilterDef(name = "void", parameters = [ParamDef(name = "isVoid", type = "boolean")])
)
@Filter(name = "void", condition = "void = :isVoid")
class MasterProduct: Auditable {

    constructor()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 35, nullable = false)
    lateinit var uid: String

    @Column(length = 50, nullable = false)
    lateinit var name: String

    @Column(length = 100)
    var description: String? = null

    @JsonIgnoreProperties("masterProduct")
    @JoinColumn(name = "product_id")
    @OneToMany(cascade = [CascadeType.ALL])
    var detailProducts: MutableSet<DetailProduct>? = null

    @JsonIgnoreProperties("masterProduct")
    @JoinColumn(name = "product_id")
    @OneToMany(cascade = [CascadeType.ALL])
    var productImages: MutableSet<DetailProductImage>? = null

    @JsonIgnoreProperties("masterProducts")
    @ManyToMany(cascade = [CascadeType.DETACH])
    @JoinTable(name = "category_product", joinColumns =
    [JoinColumn(name = "product_id")],
        inverseJoinColumns =
        [JoinColumn(name = "category_id")])
    var category: MutableSet<ProductCategory>? = null

}