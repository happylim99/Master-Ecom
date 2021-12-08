package com.sean.scheduler.util

import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

/*
@Service
class AppUtil: ApplicationContextAware {

    private lateinit var context: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    fun <T: Any> getBean(clazz: KClass<T>): Any {
        return context.getBean(clazz)
    }

    fun getBean(str: String) = context.getBean(str)

    fun <T> getAllBean(clazz: Class<T>): Array<String> =
        context.getBeanNamesForType(clazz)

}
 */
