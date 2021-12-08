package com.sean.auth.service

import com.sean.auth.entity.User
import com.sean.auth.ui.req.UserCreateReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.UserRes
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface UserService: UserDetailsService {

    fun createOne(userReq: UserCreateReq): UserRes
//    fun createPure(user: User): User
    fun updateOne(userReq: UserUptReq): UserRes
    fun showOne(id: String): UserRes?
    fun showAll(): List<User>
    fun showPag(page: Int, limit: Int): List<UserRes>
    fun addRole(userId: String, role: String)
    fun findByEmail(email: String): User?
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse)
}