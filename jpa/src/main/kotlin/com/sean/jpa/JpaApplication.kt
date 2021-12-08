package com.sean.jpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages= ["com.sean"])
class JpaApplication

fun main(args: Array<String>) {
	runApplication<JpaApplication>(*args)
}
