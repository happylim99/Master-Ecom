package com.sean.auth.ui.req

data class AddrCreateReq(
    var addressId: String = "",
    var city: String = "",
    var country: String = "",
    var streetName: String = "",
    var postalCode: String = "",
    var type: String = ""
)

data class AddrUptReq(
    var addressId: String = "",
    var city: String = "",
    var country: String = "",
    var streetName: String = "",
    var postalCode: String = "",
    var type: String = ""
)