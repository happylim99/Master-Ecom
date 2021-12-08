package com.sean.auth.email

import com.sean.auth.config.GmailConfig
import org.springframework.stereotype.Component
import java.util.*
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session


@Component
class InitEmail(
    private val gmailConfig: GmailConfig
) {

    enum class PROP(val value: String) {
        SMTP_AUTH("mail.smtp.auth"),
        SMTP_STARTTLS("mail.smtp.starttls.enable"),
        SMTP_HOST("mail.smtp.host"),
        SMTP_PORT("mail.smtp.port"),
        SMTP_SSL("mail.smtp.ssl.trust"),
        SMTP_SSL_PROTOCOL("mail.smtp.ssl.protocols")
    }

    fun prop() = Properties().apply {
        put(PROP.SMTP_AUTH.value, gmailConfig.smtpAuth)
        put(PROP.SMTP_STARTTLS.value, gmailConfig.smtpStarttls)
        put(PROP.SMTP_HOST.value, gmailConfig.smtpHost)
        put(PROP.SMTP_PORT.value, gmailConfig.smtpPort)
        put(PROP.SMTP_SSL.value, gmailConfig.smtpHost)
        put(PROP.SMTP_SSL_PROTOCOL.value, gmailConfig.smtpSslProtocol)
//        put("mail.smtp.auth", gmailConfig.smtpAuth)
//        put("mail.smtp.starttls.enable", gmailConfig.smtpStarttls);
//        put("mail.smtp.host", gmailConfig.smtpHost);
//        put("mail.smtp.port", gmailConfig.smtpPort);
//        put("mail.smtp.ssl.trust", gmailConfig.smtpHost);
    }

//    fun session(): Session = Session.getInstance(prop(), object : Authenticator() {
//        protected val passwordAuthentication: PasswordAuthentication?
//            protected get() = PasswordAuthentication(gmailConfig.smtpUsername,
//                gmailConfig.smtpPassword.toCharArray())
//        val passwordAuthentication = PasswordAuthentication(gmailConfig.smtpUsername, gmailConfig.smtpPassword.toCharArray())
//    })
     fun session(): Session = Session.getDefaultInstance(prop(), object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(gmailConfig.smtpUsername, gmailConfig.smtpPassword)
        }
})
}