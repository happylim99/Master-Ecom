package com.sean.auth.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurity(
    private val userDetailsService: UserDetailsService,
    private val bcrypt: BCryptPasswordEncoder
): WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
            ?.passwordEncoder(bcrypt)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/user/createOne")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(getAuthenticationFilter())
            .addFilterBefore(AuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    fun getAuthenticationFilter(): AuthenticationFilter? {
        val filter = AuthenticationFilter(authenticationManager())
        filter.setFilterProcessesUrl("/user/login")
        return filter
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        web?.let {
            it.ignoring()
                .antMatchers("/test/**")
        }
    }
}