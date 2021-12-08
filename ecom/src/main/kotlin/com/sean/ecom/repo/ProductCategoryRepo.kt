package com.sean.ecom.repo

import com.sean.ecom.entity.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface ProductCategoryRepo: JpaRepository<ProductCategory, Long> {

    fun findByUid(uid: String): ProductCategory?
    fun deleteByUid(uid: String): Long
}