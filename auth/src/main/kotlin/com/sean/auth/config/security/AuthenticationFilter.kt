package com.sean.auth.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.entity.User
import com.sean.auth.service.UserService
import com.sean.auth.ui.req.LoginReq
import com.sean.auth.util.AuthUtil
import com.sean.base.util.SpringContext
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java),
    private val authUtil: AuthUtil = SpringContext.getBean(AuthUtil::class.java)
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?,
                                       response: HttpServletResponse?): Authentication {
        try {
            val cred = mapper.readValue(request?.inputStream, LoginReq::class.java)
            val authToken = UsernamePasswordAuthenticationToken(cred.email, cred.passwd)
            return authManager.authenticate(authToken)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val coreUser = authResult?.principal as org.springframework.security.core.userdetails.User
        val access_token = authUtil.genAccessToken(coreUser.username,
            request?.requestURL.toString(), coreUser.authorities.map { it.authority }.toList())
        val refresh_token = authUtil.genRefreshToken(coreUser.username,
            request?.requestURL.toString(), coreUser.authorities.map { it.authority }.toList())
        val user: User? = (SpringContext.getBean("userServiceImpl") as UserService)
            .findByEmail(coreUser.username)
        response?.addHeader(SecurityConst.HEADER_STRING, SecurityConst.TOKEN_PREFIX + access_token)
        response?.contentType = "application/json"

        authUtil.authSuccessRes(response, access_token, refresh_token)
    }

    fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
        return this.bufferedReader(charset).use { it.readText() }
    }
}