package com.sean.scheduler.service

import org.hibernate.Filter
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.EntityManager

abstract class BaseSrv<T: Any, Repo: JpaRepository<T, Long>>(

) {
    @Autowired
    protected lateinit var em: EntityManager

    protected fun customFindAll(void: Boolean): List<T> {
        val session: Session = em.unwrap(Session::class.java)
        val filter: Filter = session.enableFilter("void")
        filter.setParameter("isVoid", void)
        var list: List<T> = getRepo().findAll()
        session.disableFilter("void")
        return list
    }

    protected fun findAll(): List<T> = getRepo().findAll()

    abstract fun getRepo(): Repo

}