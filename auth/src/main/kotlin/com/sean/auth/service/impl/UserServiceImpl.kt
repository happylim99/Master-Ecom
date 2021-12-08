package com.sean.auth.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.config.security.SecurityConst
import com.sean.auth.controller.UserController
import com.sean.auth.entity.User
import com.sean.auth.exception.UserServiceException
import com.sean.auth.repo.RoleRepo
import com.sean.auth.repo.UserRepo
import com.sean.auth.service.UserService
import com.sean.auth.ui.req.UserCreateReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.ErrorMsgList
import com.sean.auth.ui.res.UserRes
import com.sean.auth.util.AuthUtil
import com.sean.auth.util.Util
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.add
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.transaction.Transactional

//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl

@Service
@Transactional
class UserServiceImpl @Autowired constructor(
    private val uRepo: UserRepo,
    private val rRepo: RoleRepo,
    private val bcrypt: BCryptPasswordEncoder,
    private val mapper: ObjectMapper,
    private val authUtil: AuthUtil
): UserService {

    private val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    private val logger: Logger = LoggerFactory.getLogger("aaa")

//    @Throws(Exception::class)
    override fun createOne(userReq: UserCreateReq): UserRes {
        log.info("saveOne")
        if(userReq.username == null || userReq.username.isBlank())
            throw UserServiceException(ErrorMsgList.MISSING_REQUIRED_FIELD.value)
        var user = User()
        BeanUtils.copyProperties(userReq, user)
        user.userId = Util.getUUID()
        user.password = bcrypt.encode(userReq.passwd)
        user = uRepo.save(user)
        var userRes = UserRes()
        BeanUtils.copyProperties(user, userRes)
        return userRes
    }

    override fun updateOne(userReq: UserUptReq): UserRes {
        var user: User = uRepo.findByUserId(userReq.userId) ?: throw Exception("Not Found")
        var userRes = UserRes()
        user?.apply {
            name = userReq.name
            username = userReq.username
            uRepo.save(this)
            BeanUtils.copyProperties(user, userRes)
        }
        return userRes
    }

//    override fun createPure(user: User): User {
//        logger.info("savePure")
//        return uRepo.save(user)
//    }

    override fun showOne(userId: String): UserRes? {
        val user: User = uRepo.findByUserId(userId) ?: throw Exception("Not Found")
        var userRes = UserRes()
        user?.let { BeanUtils.copyProperties(user, userRes) }
//        userRes.add(linkTo<UserController> { showOne(id) }.withSelfRel())
//        userRes.add(linkTo<UserController> { showOne(userId) }.withRel("aa"))
        return userRes
    }

    override fun showAll(): List<User> {
        return uRepo.findAll()
    }

    override fun showPag(page: Int, limit: Int): MutableList<UserRes> {
        val pagReq = PageRequest.of(page, limit)
        val userPage = uRepo.findAll(pagReq)
        var rtnValue: MutableList<UserRes> = mutableListOf()
        userPage.content.forEach {
            var userRes = UserRes()
            BeanUtils.copyProperties(it, userRes)
            rtnValue.add(userRes)
        }
        return rtnValue
    }

    override fun addRole(userId: String, role: String) {
        var user = uRepo.findByUserId(userId)
        val role = rRepo.findByName(role)
        user?.roles?.add(role)
    }

//    override fun addRole(email: String, role: String) {
//        var user = uRepo.findByEmail(email)
//        val role = rRepo.findByName(role)
//        user?.roles?.add(role)
//    }

    override fun findByEmail(email: String): User? {
        return uRepo.findByEmail(email)
    }

    override fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        val authHeader: String = request.getHeader(SecurityConst.HEADER_STRING)
        if(authHeader != null && authHeader.startsWith(SecurityConst.TOKEN_PREFIX)) {
            try {
//                val token = authHeader.replace(SecurityConst.TOKEN_PREFIX, "")
//                val algorithm = Algorithm.HMAC512(SecurityConst.getSecretToken())
//                val jwtVerifier = JWT.require(algorithm).build()
//                val decodeJwt = jwtVerifier.verify(token)
//                val username = decodeJwt.subject
                val decodeJwt = authUtil.decodeJwt(request, response)
                val coreUser = findByEmail(decodeJwt.subject)
//                val access_token = JWT.create()
//                    .withSubject(coreUser?.username)
//                    .withExpiresAt(Date(System.currentTimeMillis() + SecurityConst.TOKEN_EXPIRATION_TIME))
//                    .withIssuer(request?.requestURL.toString())
//                    .withClaim("roles", coreUser?.roles?.map { it.name }?.toList())
//                    .sign(algorithm)
                val access_token = authUtil.genAccessToken(coreUser?.username,
                    request?.requestURL.toString(), coreUser?.let { it.roles.map { it.name }.toList() })
//                val refresh_token = JWT.create()
//                    .withSubject(coreUser?.username)
//                    .withExpiresAt(Date(System.currentTimeMillis() + SecurityConst.REFRESH_EXPIRATION_TIME))
//                    .withIssuer(request?.requestURL.toString())
//                    .withClaim("roles", coreUser?.roles?.map { it.name }?.toList())
//                    .sign(algorithm)
                val refresh_token = authUtil.genRefreshToken(coreUser?.username,
                    request?.requestURL.toString(), coreUser?.let { it.roles.map { it.name }.toList() })
                response?.addHeader(SecurityConst.HEADER_STRING, SecurityConst.TOKEN_PREFIX + access_token)
                response?.contentType = "application/json"

                authUtil.authSuccessRes(response, access_token, refresh_token)
//                response?.let {
//                    var res = mutableMapOf<String, String>()
//                    res["access_token"] = access_token
//                    res["refresh_token"] = refresh_token
//                    mapper.writeValue(response.outputStream, res)
//                }
            } catch (e: Exception) {
                log.error("AuthorizationFilter error $e")
                authUtil.authFailRes(response, e)
//                response.setHeader("error", e.message)
//                response.status = HttpStatus.FORBIDDEN.value()
//                response?.let {
//                    var res = mutableMapOf<String, String>()
//                    res["error_message"] = e.message.toString()
//                    mapper.writeValue(response.outputStream, res)
//                }
            }

        } else {
            throw Exception("Missing refresh token")
        }
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = email?.let{
            uRepo.findByEmail(email)} ?: throw UsernameNotFoundException("$email not found")

        val authorities:MutableCollection<SimpleGrantedAuthority> = mutableListOf()
        user?.roles?.forEach { role ->
            authorities.add(SimpleGrantedAuthority(role.name))
        }
        return org.springframework.security.core.userdetails.User(
            user?.email, user?.password, authorities
        )
    }
}