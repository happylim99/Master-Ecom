package com.sean.scheduler.repo

import com.sean.scheduler.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface TaskRepo: JpaRepository<Task, Long> {

    fun findByUid(uid: String): Task?
    fun deleteByUid(uid: String): Long
    fun findByScheduleTrueAndVoidFalse(): List<Task>?

}