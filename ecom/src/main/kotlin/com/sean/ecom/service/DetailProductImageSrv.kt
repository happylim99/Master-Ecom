package com.sean.ecom.service

import com.sean.ecom.entity.DetailProductImage
import org.springframework.web.multipart.MultipartFile

interface DetailProductImageSrv {
    fun createOne(file: MultipartFile, productUid: String): DetailProductImage
    fun showOne(uid: String): DetailProductImage?
    fun showAllValid(): List<DetailProductImage>?
    fun showAllVoid(): List<DetailProductImage>?
    fun showAll(): List<DetailProductImage>?
    fun deleteOne(uid: String): String
}