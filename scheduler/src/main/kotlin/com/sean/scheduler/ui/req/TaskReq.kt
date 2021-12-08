package com.sean.scheduler.ui.req

data class TaskCrtReq(
    var beanName: String,
    var cronJob: String,
    var groupName: String,
    var schedule: Boolean,
)

data class TaskUptReq(
    var beanName: String,
    var cronJob: String,
    var groupName: String,
    var schedule: Boolean,
)
