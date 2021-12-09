package com.sean.auth.service

import com.sean.auth.entity.User
import com.sean.auth.ui.req.UserCrtReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.UserRes
import com.sean.base.service.Crud
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface UserService: UserDetailsService, Crud<UserRes, UserCrtReq, UserUptReq> {

    fun showPag(page: Int, limit: Int): List<UserRes>
    fun addRole(userId: String, role: String)
    fun findByEmail(email: String): User?
    fun findByEmailReturnUserRes(email: String): UserRes?
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse)
}