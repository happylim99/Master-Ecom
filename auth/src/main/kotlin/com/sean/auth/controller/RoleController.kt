package com.sean.auth.controller

import com.sean.auth.entity.Role
import com.sean.auth.entity.User
import com.sean.auth.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/role")
class RoleController @Autowired constructor(
    private val roleSrv: RoleService
) {

    @PostMapping("/saveOne")
    fun saveOne(@RequestBody role: Role): ResponseEntity<Role> {
        return ResponseEntity.ok(roleSrv.saveOne(role))
    }
}