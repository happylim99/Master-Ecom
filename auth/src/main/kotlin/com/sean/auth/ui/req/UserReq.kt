package com.sean.auth.ui.req

data class UserCrtReq(
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var passwd: String = "",
    var confirmPasswd: String = ""
)

data class UserUptReq(
    var name: String = "",
    var username: String = ""
)

data class PasswdChangeReq(
    var passwd: String = "",
    var confirmPasswd: String = ""
)