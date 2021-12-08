package com.sean.auth.dto

import java.io.Serializable

data class UserDto(
    var id: Long = 0,
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var passwd: String = "",
    var encryptedPasswd: String = "",
    var emailVerificationToken: String = "",
    var emailVerificationStatus: Boolean = false
): Serializable
