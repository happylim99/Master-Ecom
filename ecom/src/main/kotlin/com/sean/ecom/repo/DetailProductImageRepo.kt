package com.sean.ecom.repo

import com.sean.ecom.entity.DetailProductImage
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface DetailProductImageRepo: JpaRepository<DetailProductImage, Long> {

    fun findByNewName(newName: String): DetailProductImage?
    fun deleteByNewName(uid: String): Long
}