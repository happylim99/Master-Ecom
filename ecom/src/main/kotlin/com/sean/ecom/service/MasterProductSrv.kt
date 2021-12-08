package com.sean.ecom.service

import com.sean.ecom.entity.MasterProduct
import com.sean.ecom.ui.req.CategoryProductReq
import com.sean.ecom.ui.req.ProductCrtReq
import com.sean.ecom.ui.req.ProductUptReq

interface MasterProductSrv: Crud<MasterProduct, ProductCrtReq, ProductUptReq> {
    fun productWantCategory(req: CategoryProductReq): MasterProduct
    fun productRemoveCategory(req: CategoryProductReq): MasterProduct
}