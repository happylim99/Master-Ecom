package com.sean.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
internal class TestUserController @Autowired constructor(
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper
) {

    private val baseUrl = "/user"

//    @Nested
//    @DisplayName("saveBank")
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    inner class createOne() {
//
//        private fun saveOne(userCreateReq: UserCreateReq): ResultActionsDsl {
//            return mockMvc.post("$baseUrl/createOne") {
//                contentType = MediaType.APPLICATION_JSON
//                content = mapper.writeValueAsString(userCreateReq)
//            }
//        }
//
//        @Test
//        fun `should createOne`() {
//            val userCreateReq = UserCreateReq("123", "123", "123@123.com", "abcd")
//            val userRes = saveOne(userCreateReq)
//            userRes
//                .andDo { print() }
//                .andExpect {
//                    status { isCreated() }
//                    content { contentType(MediaType.APPLICATION_JSON) }
//                    jsonPath("$.name") {
//                        value("123")
//                    }
//                }
//        }
//
//    }

//    @Nested
//    @DisplayName("showOne()")
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    inner class showOne {
//        @Test
//        fun `should find accountNumber 1234`() {
//            // given
//            val accountNumber = 1234
//
//            // when/then
//            mockMvc.get("$baseUrl/showOne/$accountNumber")
//                .andDo { print() }
//                .andExpect {
//                    status { isOk() }
//                    content { contentType(MediaType.APPLICATION_JSON) }
//                    jsonPath("$.trust") {
//                        value(0.0)
//                    }
//                    jsonPath("$.transactionFee") {
//                        value(1)
//                    }
//                }
//        }
//
//        @Test
//        fun `should return Not Found if account not exist`() {
//            val accountNumber = "doest not exist"
//
//            mockMvc.get("$baseUrl/showOne/$accountNumber")
//                .andDo { print() }
//                .andExpect {
//                    status { isNotFound() }
//                }
//        }
//    }

}