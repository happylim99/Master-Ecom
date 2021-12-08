package com.sean.auth.config.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*
class AuthorizationFilter_bak(
    private val authManager: AuthenticationManager
): BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            val authentication = getAuthentication(request)
            if (authentication == null) {
                chain.doFilter(request, response)
                return
            }
            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(request, response)
            return
        } catch (failed: AuthenticationException) {
            SecurityContextHolder.clearContext()
            onUnsuccessfulAuthentication(request, response, failed)
            chain.doFilter(request, response)
            return
        }
    }

    private fun getAuthentication(req: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        var token = req.getHeader(SecurityConst.HEADER_STRING)
//        if(token != null && token.startsWith(SecurityConst.TOKEN_PREFIX)) {
//            token = token.replace(SecurityConst.TOKEN_PREFIX, "")
//            val user: String = Jwts.parser()
//                .setSigningKey(SecurityConst.getSecretToken())
//                .parseClaimsJws(token)
//                .body
//                .subject
//            val user: String = Jwts.parser()
//                .setSigningKey(SecurityConst.getSecretToken())
//                .parseClaimsJws(token)
//                .body
//                .subject
//        }

        token?.let {
            token = token.replace(SecurityConst.TOKEN_PREFIX, "")
            val user: String = Jwts.parser()
                .setSigningKey(SecurityConst.getSecretToken())
                .parseClaimsJws(token)
                .body
                .subject
            return if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, ArrayList())
            } else null
        } ?: return null
//        if (token != null) {
//            token = token.replace(SecurityConst.TOKEN_PREFIX, "")
//            val user: String = Jwts.parser()
//                .setSigningKey(SecurityConst.getSecretToken())
//                .parseClaimsJws(token)
//                .body
//                .subject
//            return if (user != null) {
//                UsernamePasswordAuthenticationToken(user, null, ArrayList())
//            } else null
//        }
//        return null
    }
}
 */