rootProject.name = "scheduler"

include(":base")
project(":base").projectDir = File("../base")

//include(":jpa")
//project(":jpa").projectDir = File("../jpa")

pluginManagement {
	plugins {
		id("org.springframework.boot") version "2.6.1"
		id("io.spring.dependency-management") version "1.0.11.RELEASE"
		kotlin("jvm") version "1.6.0"
		kotlin("plugin.spring") version "1.6.0"
		kotlin("plugin.jpa") version "1.6.0"
		kotlin("plugin.serialization") version "1.6.0"
	}
}