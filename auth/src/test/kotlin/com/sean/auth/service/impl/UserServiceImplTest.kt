package com.sean.auth.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.entity.User
import com.sean.auth.repo.RoleRepo
import com.sean.auth.repo.UserRepo
import com.sean.auth.service.UserService
import com.sean.auth.ui.req.UserCreateReq
import com.sean.auth.ui.res.UserRes
import com.sean.auth.util.AuthUtil
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.fail
import org.mockito.Mock
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

internal class UserServiceImplTest {

    val userId = "abcde12345"
    val encryptedPassword = "abcde12345"

    @RelaxedMockK
    private lateinit var userRepo: UserRepo
    @MockK
    private lateinit var roleRepo: RoleRepo
    @MockK
    private lateinit var bcrypt: BCryptPasswordEncoder
    @MockK
    private lateinit var mapper: ObjectMapper
    @MockK
    private lateinit var authUtil: AuthUtil

    @InjectMockKs
    private lateinit var usrSrv: UserServiceImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createOne() {
        every { bcrypt.encode(any()) } returns encryptedPassword
        every { userRepo.save(any()) } returns getUser()
        val userRes = usrSrv.createOne(getUserCreateReq())
        assertEquals("1a", userRes?.userId)
    }

    @Test
    fun showAll() {
//        fail("implementing")
    }

    @Test
    fun showOne() {
        every { userRepo.findByUserId(any()) } returns getUser()
        val userRes = usrSrv.showOne("1a")
        assertEquals("1a", userRes?.userId)
        assertTrue(true)
    }

    @Test
    fun `showOne throw exception`() {
        every { userRepo.findByUserId(any()) } returns null
        org.junit.jupiter.api.assertThrows<Exception> { usrSrv.showOne("1a") }
    }

    private fun getUser(): User {
        val user = User(1L, "1a", "aaa",
            "aaa", "aaa@aaa.com", "token",
            false, "abcd", mutableSetOf())
        return user
    }

    private fun getUserCreateReq(): UserCreateReq {
        val userReq = UserCreateReq("aaa", "aaa",
            "aaa@aaa.com", encryptedPassword)
        return userReq
    }
}