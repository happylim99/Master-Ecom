package com.sean.auth.service

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface AuthService {

    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse)
}