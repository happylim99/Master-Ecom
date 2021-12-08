package com.sean.auth.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@Configuration
class AuthConfig {

    @Bean
    fun bcrypt() = BCryptPasswordEncoder()

    @Bean
    fun mapper() = ObjectMapper()

}