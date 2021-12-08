package com.sean.scheduler.service.impl

import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.scheduler.entity.Task
import com.sean.scheduler.repo.TaskRepo
import com.sean.scheduler.service.BaseSrv
import com.sean.scheduler.service.TaskSrv
import com.sean.scheduler.ui.req.TaskCrtReq
import com.sean.scheduler.ui.req.TaskUptReq
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskSrvImpl(
    private val repo: TaskRepo,
    private val scheduler: Scheduler
) : BaseSrv<Task, TaskRepo>(

), TaskSrv {

    override fun getRepo() = repo

    override fun toggleSchedule(uid: String): String {
        var task = repo.findByUid(uid)
        if(task == null) {
            throw CException("Record not found");
        } else {
            task.schedule = !task.schedule
            task = repo.save(task)
            scheduler.toggleSchedule(task)
            return "OK"
        }
    }

    override fun createOne(req: TaskCrtReq): Task {
    	var obj = Task()
    	BeanUtils.copyProperties(req, obj)
    	obj.uid = getUUID()
    	val task = repo.save(obj)
        scheduler.toggleSchedule(task)
        return task
    }

    override fun updateOne(uid: String, req: TaskUptReq): Task {
    	var dbObj = repo.findByUid(uid) ?: throw CException("Object not found")
    	var obj = Task()
    	BeanUtils.copyProperties(dbObj, obj, "id")
    	BeanUtils.copyProperties(req, obj as Any, "id")
    	repo.deleteByUid(uid)
        val task = repo.save(obj)
        scheduler.toggleSchedule(task)
        return task
    }

    override fun showOne(uid: String) = repo.findByUid(uid)

    override fun deleteOne(uid: String): String {
    	val taskId = repo.deleteByUid(uid)
        return if(taskId.equals(1)) {
            repo.findByUid(uid)?.let {
                scheduler.removeTaskFromScheduler(it.beanName)
            }
            "OK"
        } else {
            "NOT OK"
        }
    }

    override fun showAllValid() = customFindAll(false)

    override fun showAllVoid() = customFindAll(true)

    override fun showAll() = findAll()

}