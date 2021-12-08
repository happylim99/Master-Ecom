package com.sean.ecom.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.*
import java.math.BigDecimal
import javax.persistence.*
import javax.persistence.CascadeType
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE detail_product SET void = true WHERE id=?")
@FilterDefs(
    FilterDef(name = "void", parameters = [ParamDef(name = "isVoid", type = "boolean")])
)
@Filter(name = "void", condition = "void = :isVoid")
class DetailProduct: Auditable {

    constructor()

    enum class STATUS {
        AVAILABLE, ON_HOLD, OUT_OF_STOCK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 35, nullable = false)
    lateinit var uid: String

    @Column(unique = true, length = 50, nullable = false)
    lateinit var sku: String

    @Column(length = 10, nullable = false)
    lateinit var unit: String

    @Column(nullable = false)
    lateinit var unitPrice: BigDecimal

    @Column(length = 100)
    var description: String? = null

    @Column
    var maxQty: Int? = null

    @Column
    var reorderQty: Int? = null

    @Column
    var qty: BigDecimal = BigDecimal.ZERO

    @Column
    var status: String = STATUS.ON_HOLD.name

    @JsonIgnoreProperties("detailProducts")
    @ManyToOne(cascade = [CascadeType.DETACH, CascadeType.MERGE,
        CascadeType.REFRESH])
    @JoinColumn(name = "product_id")
    var masterProduct: MasterProduct? = null

    @Column
    var active: Boolean = false
}