package com.sean.scheduler.service

import com.sean.scheduler.entity.Task
import com.sean.scheduler.ui.req.TaskCrtReq
import com.sean.scheduler.ui.req.TaskUptReq

interface TaskSrv: Crud<Task, TaskCrtReq, TaskUptReq> {
    fun toggleSchedule(uid: String): String
}