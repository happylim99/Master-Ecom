package com.sean.ecom.ui.req

data class DetailProductImageCrtReq(
    var path: String? = null,
    var description: String? = null
)

data class DetailProductImageUptReq(
    var name: String? = null,
    var description: String? = null
)
