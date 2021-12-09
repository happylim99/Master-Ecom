package com.sean.auth.controller

import com.sean.auth.service.UserService
import com.sean.auth.ui.req.UserCrtReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.UserRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController @Autowired constructor(
    private val srv: UserService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(@RequestBody userReq: UserCrtReq): ResponseEntity<UserRes> {
        return ResponseEntity.ok(srv.createOne(userReq))
    }

    @PutMapping("/{uid}")
    fun updateOne(@PathVariable uid: String,
                  @RequestBody userReq: UserUptReq): ResponseEntity<UserRes> {
        return ResponseEntity.ok(srv.updateOne(uid, userReq))
    }

    @GetMapping("/{uid}")
    fun showOne(@PathVariable uid: String) = ResponseEntity.ok(srv.showOne(uid))

    @GetMapping("/showAllValid")
    fun showAllValid() = ResponseEntity.ok(srv.showAllValid())

    @GetMapping("/showAllVoid")
    fun showAllVoid() = ResponseEntity.ok(srv.showAllVoid())

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = ResponseEntity.ok(srv.deleteOne(id))

    @GetMapping("/showPag")
    fun showPag(@RequestParam(value = "page", defaultValue = "1") page: Int,
                @RequestParam(value = "limit", defaultValue = "5") limit:Int
    ): ResponseEntity<List<UserRes>> {
        return ResponseEntity.ok(srv.showPag(page, limit))
    }

    @GetMapping("/refreshToken")
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        srv.refreshToken(request, response)
    }
}