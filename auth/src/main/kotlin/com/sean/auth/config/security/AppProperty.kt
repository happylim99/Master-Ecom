package com.sean.auth.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component("AppProperty")
class AppProperty @Autowired constructor(
    private val env: Environment
) {

    fun getSecretToken() = env.getProperty("secret.token")
}