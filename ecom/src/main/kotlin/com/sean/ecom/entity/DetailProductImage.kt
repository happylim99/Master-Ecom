package com.sean.ecom.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import com.sean.ecom.util.SystemUtil
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import org.hibernate.annotations.SQLDelete

@Entity
@SQLDelete(sql = "UPDATE product_image SET void = true WHERE id=?")
@FilterDef(name = "void", parameters = [ParamDef(name = "isVoid", type = "boolean")])
@Filter(name = "void", condition = "void = :isVoid")
class DetailProductImage: Auditable {

    companion object {
        const val basePath: String = "/ecom/productImage/"
        fun getBasePath() = "${SystemUtil.getRoot()}$basePath"
    }

    constructor()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 100, nullable = false)
    lateinit var path: String

    @Column(length = 100, nullable = false)
    lateinit var oriName: String

    @Column(length = 35, nullable = false)
    lateinit var newName: String

    @JsonIgnoreProperties("productImages")
    @ManyToOne(cascade = [CascadeType.DETACH, CascadeType.MERGE,
        CascadeType.REFRESH])
    @JoinColumn(name = "product_id")
    var masterProduct: MasterProduct? = null

}