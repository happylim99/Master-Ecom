package com.sean.auth.repo

import com.sean.auth.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepo: JpaRepository<Role, Long> {

    fun findByName(name: String): Role
}