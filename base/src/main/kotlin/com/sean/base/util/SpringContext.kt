package com.sean.base.util

import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class SpringContext: ApplicationContextAware {

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    companion object {
        private lateinit var context: ApplicationContext

        fun <T> getBean(clazz: Class<T>): T {
            return context.getBean(clazz)
        }

        fun getBean(str: String) = context.getBean(str)

        fun <T> getAllBean(clazz: Class<T>): Array<String> =
            context.getBeanNamesForType(clazz)
    }

}