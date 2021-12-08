package com.sean.scheduler.controller

import com.sean.scheduler.entity.Task
import com.sean.scheduler.service.TaskSrv
import com.sean.scheduler.ui.req.TaskCrtReq
import com.sean.scheduler.ui.req.TaskUptReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/task")
class TaskController @Autowired constructor(
    private val srv: TaskSrv
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(@RequestBody req: TaskCrtReq) =
    	ResponseEntity.ok(srv.createOne(req))

    @PutMapping("/{uid}")
    fun updateOne(@PathVariable uid: String,
    			  @RequestBody req: TaskUptReq
    ): ResponseEntity<Task> {
    	return ResponseEntity.ok(srv.updateOne(uid, req))
    }

    @GetMapping("/{uid}")
    fun showOne(@PathVariable uid: String) = ResponseEntity.ok(srv.showOne(uid))

    @GetMapping("/showAllValid")
    fun showAllValid() = ResponseEntity.ok(srv.showAllValid())

    @GetMapping("/showAllVoid")
    fun showAllVoid() = ResponseEntity.ok(srv.showAllVoid())

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{uid}")
    fun deleteOne(@PathVariable uid: String) = ResponseEntity.ok(srv.deleteOne(uid))

    @GetMapping("toggleSchedule/{uid}")
    fun toggleSchedule(@PathVariable uid: String) = ResponseEntity.ok(srv.toggleSchedule(uid))
}