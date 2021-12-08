package com.sean.scheduler.task

import com.sean.scheduler.service.impl.MasterSchedulerSrvImpl
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskTwo: MasterTask() {

    override fun run() {
        println("${Date()} TaskTwo runnable here")
    }

}