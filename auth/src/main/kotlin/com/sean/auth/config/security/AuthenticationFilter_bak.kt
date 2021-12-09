package com.sean.auth.config.security

/*
class AuthenticationFilter_bak(
    private val authManager: AuthenticationManager,
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java)
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?,
                                       response: HttpServletResponse?): Authentication {
        try {
//            val username = request?.getParameter("username")
//            val passwd = request?.getParameter("passwd")
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
        val email = coreUser.username
        val roles = listOf<String>("aaa", "bbb")
        val authorities = mutableMapOf("roles" to roles)
        val token: String = Jwts.builder()
            .setSubject(email)
            .setClaims(authorities as Map<String, Any>?)
            .setExpiration(Date(System.currentTimeMillis() + SecurityConst.EXPIRATION_TIME))
            .signWith(
                SignatureAlgorithm.HS512,
                SecurityConst.getSecretToken() ?: "abcd".toBase64())
            .compact()

        val user: User? = (SpringContext.getBeanByName("userServiceImpl") as UserService)
            .findByEmail(email)

        response?.addHeader(SecurityConst.HEADER_STRING, SecurityConst.TOKEN_PREFIX + token)
        response?.addHeader("UserID", user?.userId.toString())
        response?.contentType = "application/json"
//        response?.writer?.write(mapper.writeValueAsString(
//            UserCreateReq("aaa", "aaa", "aaa@aaa.com", "abcd")
//        ))
        response?.let {
            var res = mutableMapOf<String, String>()
            res["access_token"] = token
            mapper.writeValue(response.outputStream, res)
        }

    }
}
 */