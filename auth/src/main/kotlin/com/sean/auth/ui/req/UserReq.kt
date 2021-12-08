package com.sean.auth.ui.req

data class UserCreateReq(
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var passwd: String = ""
)

data class UserUptReq(
    var userId: String = "",
    var name: String = "",
    var username: String = "",
)

data class UserTestReq(
    var userId: String = "",
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var passwd: String = ""
)