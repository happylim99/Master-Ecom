package com.sean.scheduler.task

import com.sean.scheduler.service.MasterSchedulerSrv
import com.sean.scheduler.service.impl.MasterSchedulerSrvImpl
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskOne: MasterTask() {

    override fun run() {
        println("${Date()} TaskOne runnable here")
    }

}