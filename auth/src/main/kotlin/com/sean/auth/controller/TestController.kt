package com.sean.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.service.TestService
import com.sean.auth.service.UserService
import com.sean.auth.ui.req.LoginReq
import com.sean.auth.ui.req.UserCreateReq
import com.sean.auth.ui.req.UserTestReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.UserRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin
@RequestMapping("/test")
class TestController @Autowired constructor(
    private val testSrv: TestService,
    private val mapper: ObjectMapper
) {

    @PostMapping("/createOne")
    fun createOne(@RequestBody req: UserTestReq): ResponseEntity<UserRes> {
//        return ResponseEntity.ok("aaaaaaaaa")
//        val userReq = UserCreateReq("111", "111", "111@111.com", "abcd")
        return ResponseEntity.ok(testSrv.createOne(req))
    }

    @PostMapping("/updateOne")
    fun updateOne(@RequestBody req: UserUptReq): ResponseEntity<Any> {
//        return ResponseEntity.ok("aaaaaaaaa")
//        val userReq = UserCreateReq("111", "111", "111@111.com", "abcd")
        return ResponseEntity.ok(testSrv.updateOne(req))
    }

    @PostMapping("/test1")
    fun test1(@RequestBody req: LoginReq): ResponseEntity<Any> {
//        val aaa = mapper.readValue(request.toString(), LoginReq::class.java)
//        println("aaaa $aaa")
        println("test1 req $req")
        return ResponseEntity.ok(testSrv.test1())
    }

    @PostMapping("/test2")
    fun test2(@RequestBody req: LoginReq): ResponseEntity<Any> {
//        val aaa = mapper.readValue(request.toString(), LoginReq::class.java)
//        println("aaaa $aaa")
        println("test2 req $req")
        return ResponseEntity.ok("hello ing")
    }
}