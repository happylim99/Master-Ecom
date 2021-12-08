package com.sean.scheduler.service.impl

import com.sean.base.util.SpringContext
import com.sean.scheduler.service.MasterSchedulerSrv
import com.sean.scheduler.task.MasterTask
import org.springframework.stereotype.Service

@Service
class MasterSchedulerSrvImpl: MasterSchedulerSrv {

    override fun showAll() = SpringContext.getAllBean(MasterTask::class.java)
}