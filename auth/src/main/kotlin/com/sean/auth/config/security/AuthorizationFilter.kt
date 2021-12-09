package com.sean.auth.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.service.impl.UserServiceImpl
import com.sean.auth.util.AuthUtil
import com.sean.base.util.SpringContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.math.absoluteValue

class AuthorizationFilter(
    private val authManager: AuthenticationManager,
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java),
    private val authUtil: AuthUtil = SpringContext.getBean(AuthUtil::class.java)
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val log: Logger = LoggerFactory.getLogger(AuthorizationFilter::class.java)

        if(request.servletPath.equals("/user/login")
            || request.servletPath.equals("/user/register")
            || request.servletPath.equals("/user/refreshToken")) {
            filterChain.doFilter(request, response)
        } else {
            val authHeader: String? = request.getHeader(SecurityConst.HEADER_STRING)
            if(authHeader != null && authHeader.startsWith(SecurityConst.TOKEN_PREFIX)) {
                try {
                    val decodeJwt = authUtil.decodeJwt(request, response)
                    val username = decodeJwt.subject
                    val roles = decodeJwt.getClaim("roles").asArray(String::class.java)
                    val authorities = roles.map {
                        SimpleGrantedAuthority(it)
                    }.toList()
                    val authToken = UsernamePasswordAuthenticationToken(username, null, authorities)
                    SecurityContextHolder.getContext().authentication = authToken
                    filterChain.doFilter(request, response)
                } catch (e: Exception) {
                    log.error("AuthorizationFilter error $e")
                    authUtil.authFailRes(response, e)
//                    response.sendError(FORBIDDEN.absoluteValue)
                }

            } else {
                filterChain.doFilter(request, response)
            }
        }
    }
}