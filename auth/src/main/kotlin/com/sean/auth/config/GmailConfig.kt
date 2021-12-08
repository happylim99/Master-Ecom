package com.sean.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "gmail")
data class GmailConfig(
    val smtpHost: String = "",
    val smtpPort: Int = 0,
    val smtpUsername: String = "",
    val smtpPassword: String = "",
    val smtpSender: String = "",
    val smtpAuth: Boolean = true,
    val smtpStarttls: Boolean = true,
    val smtpSslProtocol: String = "TLSv1.2"
)