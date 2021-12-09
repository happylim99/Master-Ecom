package com.sean.auth.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.config.security.SecurityConst
import com.sean.auth.entity.User
import com.sean.auth.repo.RoleRepo
import com.sean.auth.repo.UserRepo
import com.sean.auth.service.UserService
import com.sean.auth.ui.req.UserCrtReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.ErrorMsgList
import com.sean.auth.ui.res.UserRes
import com.sean.auth.util.AuthUtil
import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.base.service.BaseSrv
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.transaction.Transactional

//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl

@Slf4j
@Service
@Transactional
class UserServiceImpl @Autowired constructor(
    private val repo: UserRepo,
    private val rRepo: RoleRepo,
    private val bcrypt: BCryptPasswordEncoder,
    private val mapper: ObjectMapper,
    private val authUtil: AuthUtil
): BaseSrv<User, UserRepo>(

), UserService {

    override fun getRepo() = repo

    override fun createOne(req: UserCrtReq): UserRes {
        if(validateCrtRequireField(req))
            throw CException(ErrorMsgList.MISSING_REQUIRED_FIELD.value)

        if(validatePassword(req))
            throw CException(ErrorMsgList.PASSWORD_MISMATCH.value)

        var user = User()
        BeanUtils.copyProperties(req, user)
        user.uid = getUUID()
        user.password = bcrypt.encode(req.passwd)
        user.emailVerificationToken = authUtil.genEmailToken()
        user = repo.save(user)
        var userRes = UserRes()
        BeanUtils.copyProperties(user, userRes)
        return userRes
    }

    override fun updateOne(uid: String, req: UserUptReq): UserRes {
        var user: User = repo.findByUid(uid) ?: throw CException("Not Found")
        var userRes = UserRes()
        user?.apply {
            name = req.name
            username = req.username
            repo.save(this)
            BeanUtils.copyProperties(user, userRes)
        }
        return userRes
    }

    override fun showOne(uid: String): UserRes? {
        val user: User = repo.findByUid(uid) ?: throw CException("Not Found")
        var userRes = UserRes()
        user?.let { BeanUtils.copyProperties(user, userRes) }
//        userRes.add(linkTo<UserController> { showOne(id) }.withSelfRel())
//        userRes.add(linkTo<UserController> { showOne(userId) }.withRel("aa"))
        return userRes
    }

    override fun showAll(): List<UserRes> {
        val users = findAll()
        var rtnValue: MutableList<UserRes> = mutableListOf()
        users?.forEach {
            var userRes = UserRes()
            BeanUtils.copyProperties(it, userRes)
            rtnValue.add(userRes)
        }
        return rtnValue
    }

    override fun showPag(page: Int, limit: Int): MutableList<UserRes> {
        val pagReq = PageRequest.of(page, limit)
        val userPage = repo.findAll(pagReq)
        var rtnValue: MutableList<UserRes> = mutableListOf()
        userPage.content.forEach {
            var userRes = UserRes()
            BeanUtils.copyProperties(it, userRes)
            rtnValue.add(userRes)
        }
        return rtnValue
    }

    override fun addRole(userId: String, role: String) {
        var user = repo.findByUid(userId)
        val role = rRepo.findByName(role)
        user?.roles?.add(role)
    }

    override fun deleteOne(uid: String): String {
        val theId = repo.deleteByUid(uid)
        return if(theId.equals(0)) "not ok" else "ok"
    }

    override fun showAllValid(): List<UserRes> {
        val users = customFindAll(false)
        var rtnValue: MutableList<UserRes> = mutableListOf()
        users?.forEach {
            var userRes = UserRes()
            BeanUtils.copyProperties(it, userRes)
            rtnValue.add(userRes)
        }
        return rtnValue
    }

    override fun showAllVoid(): List<UserRes> {
        val users = customFindAll(true)
        var rtnValue: MutableList<UserRes> = mutableListOf()
        users?.forEach {
            var userRes = UserRes()
            BeanUtils.copyProperties(it, userRes)
            rtnValue.add(userRes)
        }
        return rtnValue
    }

//    override fun addRole(email: String, role: String) {
//        var user = uRepo.findByEmail(email)
//        val role = rRepo.findByName(role)
//        user?.roles?.add(role)
//    }

    override fun findByEmail(email: String) = repo.findByEmail(email)

    override fun findByEmailReturnUserRes(email: String): UserRes? {
        val user = repo.findByEmail(email)
        var userRes = UserRes()
        user?.let { BeanUtils.copyProperties(it, userRes) }
        return userRes
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
            repo.findByEmail(email)} ?: throw CException("$email not found")

        val authorities:MutableCollection<SimpleGrantedAuthority> = mutableListOf()
        user?.roles?.forEach { role ->
            authorities.add(SimpleGrantedAuthority(role.name))
        }
        return org.springframework.security.core.userdetails.User(
            user?.email, user?.password, authorities
        )
    }

    private fun validateCrtRequireField(req: UserCrtReq): Boolean {
        if(req.name.isNullOrBlank() || req.username.isNullOrBlank()
            || req.email.isNullOrBlank() || req.passwd.isNullOrBlank()
            || req.confirmPasswd.isNullOrBlank()
        ) {
            return true
        }
        return false
    }

    private fun validatePassword(req: UserCrtReq): Boolean {
        if(req.passwd != req.confirmPasswd) {
            return true
        }
        return false
    }
}