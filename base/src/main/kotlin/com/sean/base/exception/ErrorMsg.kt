package com.sean.base.exception

import java.util.*

data class ErrorMsg(
    var timestamp: Date = Date(),
    var msg: String = ""
)
