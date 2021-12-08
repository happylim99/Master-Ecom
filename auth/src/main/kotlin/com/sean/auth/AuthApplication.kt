package com.sean.auth

import com.sean.auth.config.GmailConfig
import com.sean.auth.data.DataFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.event.EventListener
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext


@SpringBootApplication
@EnableConfigurationProperties(GmailConfig::class)
class AuthApplication {

	@Autowired
	private lateinit var appContext: ApplicationContext

//	@EventListener(ApplicationReadyEvent::class)
//	fun dataFactory() {
//		DataFactory.createData()
//	}

//	@EventListener(ApplicationReadyEvent::class)
//	fun displayAllBeans() {
//		val allBeanNames: Array<String> = appContext.beanDefinitionNames
//		for (beanName in allBeanNames) {
//			println(beanName)
//		}
//	}
}

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}





