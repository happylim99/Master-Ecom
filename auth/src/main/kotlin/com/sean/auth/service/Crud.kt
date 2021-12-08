package com.sean.auth.service

import com.sean.auth.entity.User
import com.sean.auth.ext.findOne
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.data.jpa.repository.JpaRepository
import kotlin.reflect.KClass

abstract class Crud<LOGC, ENTITY, RES, REPO: JpaRepository<ENTITY, Long>>(
    logC: Class<LOGC>,
    private val entity: Class<ENTITY>,
    private val resType: Class<RES>
) {
    private val log: Logger = LoggerFactory.getLogger(logC)

    fun <T: Any> createOne(req: T): RES {
        log.info("Generic createOne")
        var dbObj = entity.newInstance()
        BeanUtils.copyProperties(req, dbObj as Any)
        dbObj = getRepo().save(dbObj)
        var res = resType.newInstance()
        BeanUtils.copyProperties(dbObj as Any, res as Any)
        return res
    }

    fun showOne(id: Long): RES {
        log.info("Generic showOne")
        var dbObj = getRepo().findOne(id)
        var res = resType.newInstance()
        BeanUtils.copyProperties(dbObj as Any, res as Any)
        return res
    }

    fun <T: Any> updateOne(req: T): RES {
        log.info("Generic updateOne")
        var dbObj = getUpdateObj(req)
        BeanUtils.copyProperties(req, dbObj as Any)
        dbObj = getRepo().save(dbObj)
        var res = resType.newInstance()
        BeanUtils.copyProperties(dbObj as Any, res as Any)
        return res
    }

//    fun showAll(): List<RES>? {
//        log.info("Generic showAll")
//        var dbObjs = getRepo().findAll()
//        var res = resType.newInstance()
//        BeanUtils.copyProperties(dbObjs as Any, res as Any)
//        return res
//    }

    abstract fun getRepo(): REPO
    abstract fun <T> getUpdateObj(req: T): ENTITY?
}

//abstract class Crud<LOGC, out RES, JPAC, REPO: JpaRepository<JPAC, Long>>
//    private val logC: Class<LOGC>,
//    private val jpaC: Class<JPAC>,
//    private val repo: REPO,
//    private val res: Class<RES>
//)
//{

//    private val logC

//    private val log: Logger = LoggerFactory.getLogger(LOGC())
//
//    fun <T: Any> createOne(req: T, clazz: KClass<T>): RES {
//        log.info("saveOne")
//
//        var dbObj = jpaC.newInstance()
//        BeanUtils.copyProperties(req, dbObj as Any)
//
//        dbObj = repo.save(dbObj)
//        var res = res.newInstance()
//        BeanUtils.copyProperties(dbObj as Any, res as Any)
//        return res
//    }

//}