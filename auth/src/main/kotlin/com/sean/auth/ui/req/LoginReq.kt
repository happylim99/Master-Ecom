package com.sean.auth.ui.req

import java.io.Serializable

@kotlinx.serialization.Serializable
data class LoginReq(
    var email: String = "",
    var passwd: String = ""
)
