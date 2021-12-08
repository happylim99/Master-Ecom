package com.sean.ecom.ui.req

import com.sean.ecom.entity.MasterProduct

data class ProductCategoryCrtReq(
    var name: String? = null,
    var description: String? = null,
    var products: MutableSet<MasterProduct>? = null
)

data class ProductCategoryUptReq(
    var name: String? = null,
    var description: String? = null,
    var products: MutableSet<MasterProduct>? = null
)