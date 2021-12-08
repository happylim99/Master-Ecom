package com.sean.ecom.service

import com.sean.ecom.entity.ProductCategory
import com.sean.ecom.ui.req.ProductCategoryCrtReq
import com.sean.ecom.ui.req.ProductCategoryUptReq

interface ProductCategorySrv: Crud<ProductCategory, ProductCategoryCrtReq, ProductCategoryUptReq> {
//    fun createOne(req: ProductCategoryCrtReq): ProductCategory
//    fun updateOne(uid: String, req: ProductCategoryUptReq): ProductCategory
//    fun showOne(uid: String): ProductCategory?
//    fun showAllValid(): List<ProductCategory>?
//    fun showAllVoid(): List<ProductCategory>?
//    fun showAll(): List<ProductCategory>?
//    fun deleteOne(uid: String): String
}