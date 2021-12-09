package com.sean.base.exception

import java.lang.RuntimeException

class CException(
    private val msg: String
): RuntimeException(msg), NoLogException {
}