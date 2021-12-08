package com.sean.auth.repo

import com.sean.auth.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
    fun findByUserId(id: String): User?
}