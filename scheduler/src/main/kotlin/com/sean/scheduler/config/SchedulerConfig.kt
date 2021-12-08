package com.sean.scheduler.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
class SchedulerConfig {

    @Bean
    fun taskScheduler(): TaskScheduler {
        val scheduler = ThreadPoolTaskScheduler().apply {
            setThreadNamePrefix("Scheduler")
            poolSize = 5
            isRemoveOnCancelPolicy = true
        }
        scheduler.initialize()
        return scheduler
    }
}