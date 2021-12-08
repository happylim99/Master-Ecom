package com.sean.ecom.repo

import com.sean.ecom.entity.DetailProduct
import org.springframework.data.jpa.repository.JpaRepository

interface DetailProductRepo: JpaRepository<DetailProduct, Long> {

    fun findByUid(skuId: String): DetailProduct?
    fun deleteByUid(uid: String): Long
    fun findByMasterProductCategoryUid(uid: String): List<DetailProduct>

}