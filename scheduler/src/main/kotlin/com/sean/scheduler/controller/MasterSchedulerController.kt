package com.sean.scheduler.controller

import com.sean.scheduler.service.MasterSchedulerSrv
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/masterScheduler")
class MasterSchedulerController(
    private val srv: MasterSchedulerSrv
) {

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())
    
}