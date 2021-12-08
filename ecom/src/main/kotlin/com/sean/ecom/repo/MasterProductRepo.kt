package com.sean.ecom.repo

import com.sean.ecom.entity.MasterProduct
import org.springframework.data.jpa.repository.JpaRepository

interface MasterProductRepo: JpaRepository<MasterProduct, Long> {

    fun findByUid(uid: String): MasterProduct?
    fun deleteByUid(uid: String): Long
}