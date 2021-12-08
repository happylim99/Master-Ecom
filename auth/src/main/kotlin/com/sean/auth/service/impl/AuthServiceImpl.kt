package com.sean.auth.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.config.security.SecurityConst
import com.sean.auth.service.AuthService
import com.sean.auth.service.UserService
import com.sean.auth.util.AuthUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class AuthServiceImpl(
    private val authUtil: AuthUtil,
    private val mapper: ObjectMapper,
    private val usrSrv: UserService
): AuthService {

    private val log: Logger = LoggerFactory.getLogger(AuthServiceImpl::class.java)

    override fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        val authHeader: String = request.getHeader(SecurityConst.HEADER_STRING)
        if(authHeader != null && authHeader.startsWith(SecurityConst.TOKEN_PREFIX)) {
            try {
                val decodeJwt = authUtil.decodeJwt(request, response)
                val coreUser = usrSrv.findByEmail(decodeJwt.subject)
                val access_token = authUtil.genAccessToken(coreUser?.username,
                    request?.requestURL.toString(), coreUser?.let { it.roles.map { it.name }.toList() })
                val refresh_token = authUtil.genRefreshToken(coreUser?.username,
                    request?.requestURL.toString(), coreUser?.let { it.roles.map { it.name }.toList() })
                response?.addHeader(SecurityConst.HEADER_STRING, SecurityConst.TOKEN_PREFIX + access_token)
                response?.contentType = "application/json"
                authUtil.authSuccessRes(response, access_token, refresh_token)
            } catch (e: Exception) {
                log.error("AuthorizationFilter error $e")
                response.setHeader("error", e.message)
                response.status = HttpStatus.FORBIDDEN.value()
                response?.let {
                    var res = mutableMapOf<String, String>()
                    res["error_message"] = e.message.toString()
                    mapper.writeValue(response.outputStream, res)
                }
            }
        } else {
            throw Exception("Missing refresh token")
        }
    }
}