package com.sean.auth.service

import com.sean.auth.config.GmailConfig
import com.sean.auth.email.InitEmail
import com.sean.auth.entity.User
import com.sean.auth.repo.UserRepo
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.UserRes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.Message
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

@Service
class TestService(
    private val gmailConfig: GmailConfig,
    private val initEmail: InitEmail,
    private val repo: UserRepo
): Crud<TestService, User, UserRes, UserRepo>(
    TestService::class.java,
    User::class.java,
    UserRes::class.java
) {

    fun test1(): Map<String, String> {
        val chars = mapOf("97" to "keya", "98" to "keyb", "120" to "keyc")
        return chars
    }

//    fun test1(): String {
//        val subject = "Testing email"
//        val body = """<body style="background-color:powderblue;">
//            |<h1>This is a heading</h1>
//            |<p>This is a paragraph.</p>
//            |</body>""".trimMargin()
//        val email = draftEmail(subject, body)
//        Transport.send(email)
//        return gmailConfig.smtpHost
//    }

    fun draftEmail(sbj: String, body: String): Message {
        var msg = MimeMessage(initEmail.session()).apply {
            setFrom(InternetAddress(gmailConfig.smtpSender))
            setRecipients(Message.RecipientType.TO, arrayOf(InternetAddress("happylim99@gmail.com")))
            subject = sbj
            val mimeMultiPart: MimeMultipart = MimeMultipart().apply {
                addBodyPart(MimeBodyPart().apply {
                    setContent(body, "text/html");
                })
            }
            setContent(mimeMultiPart)
        }
        return msg
    }

    override fun getRepo(): UserRepo {
        return repo
    }

    override fun <T> getUpdateObj(req: T): User? =
        repo.findByUserId((req as UserUptReq).userId) ?: throw Exception("User not found")
}