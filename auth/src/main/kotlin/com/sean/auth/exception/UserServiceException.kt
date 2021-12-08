package com.sean.auth.exception

import java.lang.RuntimeException

class UserServiceException(
    private val msg: String
): RuntimeException(msg) {
}