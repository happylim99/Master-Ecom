package com.sean.auth.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.sean.auth.config.security.SecurityConst
import com.sean.auth.entity.User
import com.sean.auth.service.UserService
import com.sean.auth.ui.req.UserCreateReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.UserRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController @Autowired constructor(
    private val usrSrv: UserService
) {

    @PostMapping("/createOne")
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(@RequestBody userReq: UserCreateReq): ResponseEntity<UserRes> {
        return ResponseEntity.ok(usrSrv.createOne(userReq))
    }

    @PostMapping("/updateOne")
    fun updateOne(@RequestBody userReq: UserUptReq): ResponseEntity<UserRes> {
        return ResponseEntity.ok(usrSrv.updateOne(userReq))
    }

    @GetMapping("/showAll")
    fun showAll(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(usrSrv.showAll())
    }

    @GetMapping("/showOne/{id}")
    fun showOne(@PathVariable id: String) =
        ResponseEntity.ok(usrSrv.showOne(id))

    @GetMapping("/showPag")
    fun showPag(@RequestParam(value = "page", defaultValue = "1") page: Int,
                @RequestParam(value = "limit", defaultValue = "5") limit:Int
    ): ResponseEntity<List<UserRes>> {
        return ResponseEntity.ok(usrSrv.showPag(page, limit))
    }

    @GetMapping("/refreshToken")
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        usrSrv.refreshToken(request, response)
    }
}