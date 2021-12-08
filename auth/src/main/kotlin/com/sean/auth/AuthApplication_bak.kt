package com.sean.auth

import com.sean.auth.entity.Role
import com.sean.auth.entity.User
import com.sean.auth.service.RoleService
import com.sean.auth.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

//@SpringBootApplication
class AuthApplicationBak {
	@Bean
	@Order(1)
	fun run(usrSrv: UserService, roleSrv: RoleService): CommandLineRunner {
		return CommandLineRunner { args ->
			roleSrv.saveOne(Role(Role.RoleName.root.name))
			roleSrv.saveOne(Role(Role.RoleName.admin.name))
			roleSrv.saveOne(Role(Role.RoleName.superuser.name))
			roleSrv.saveOne(Role(Role.RoleName.user.name))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}



