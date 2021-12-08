package com.sean.scheduler.service.impl

import com.sean.base.annotation.Slf4j
import com.sean.base.util.SpringContext
import com.sean.scheduler.entity.Task
import com.sean.scheduler.repo.TaskRepo
import com.sean.scheduler.task.MasterTask
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ScheduledFuture
import kotlin.collections.HashMap

@Service
@Slf4j
class Scheduler (
    private val scheduler: TaskScheduler,
    private val repo: TaskRepo
) {

    private val log = LoggerFactory.getLogger(Scheduler::class.java)

    var jobsMap = HashMap<String, ScheduledFuture<*>?>()

    @EventListener(ContextRefreshedEvent::class)
    fun contextRefreshedEvent() {
        log.info("Initiate Task")
        val tasks = repo.findByScheduleTrueAndVoidFalse()
        tasks?.forEach {
            addTaskToScheduler(it.beanName, it.cronJob, SpringContext.getBean(it.beanName) as Runnable)
        }
    }

    fun addTaskToScheduler(beanName: String, cronStr: String, task:Runnable) {
        if(isTaskRunning(beanName)) {
            removeTaskFromScheduler(beanName)
        }
        jobsMap[beanName] = scheduler.schedule(
            task,
            CronTrigger(cronStr, TimeZone.getTimeZone(TimeZone.getDefault().id))
        )
        (SpringContext.getBean(beanName) as MasterTask).run()
    }

    fun isTaskRunning(beanName: String) =
        jobsMap.entries.any { entry -> entry.key == beanName && entry.value != null }

    fun removeTaskFromScheduler(beanName: String) {
        jobsMap[beanName]?.apply {
            if(cancel(true)) {
                jobsMap[beanName] = null
            }
        }
    }

    fun toggleSchedule(task: Task) {
        log.info("Toggle schedule ${task.beanName} ${task.schedule}")
        if(isTaskRunning(task.beanName)) {
            removeTaskFromScheduler(task.beanName)
        }

        if(task.schedule) {
            addTaskToScheduler(task.beanName, task.cronJob, SpringContext.getBean(task.beanName) as Runnable)
        }
    }
}