package com.sean.ecom.ui.req

import java.math.BigDecimal

data class ProductCrtReq(
    var name: String? = null,
    var description: String? = null
)

data class ProductUptReq(
    var name: String? = null,
    var description: String? = null
)

data class CategoryProductReq(
    val productUid: String,
    val categoryUid: String
)

data class DetailProductCrtReq(
    var sku: String,
    var unit: String,
    var unitPrice: BigDecimal,
    var description: String?,
    var maxQty: Int?,
    var reorderQty: Int?,
    var qty: BigDecimal?,
    var status: String?,
    var productUid: String?
)

data class DetailProductUptReq(
    var sku: String,
    var unit: String,
    var unitPrice: BigDecimal,
    var description: String?,
    var maxQty: Int?,
    var reorderQty: Int?,
    var qty: BigDecimal?,
    var status: String?,
    var productUid: String?
)