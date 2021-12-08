package com.sean.ecom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages= ["com.sean"])
//@EntityScan(basePackages = ["com.sean.scheduler.entity", "com.sean.ecom.entity"])
class EcomApplication

fun main(args: Array<String>) {
	runApplication<EcomApplication>(*args)
}
