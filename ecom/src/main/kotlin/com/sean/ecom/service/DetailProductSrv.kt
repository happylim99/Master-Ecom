package com.sean.ecom.service

import com.sean.ecom.entity.DetailProduct
import com.sean.ecom.ui.req.DetailProductCrtReq
import com.sean.ecom.ui.req.DetailProductUptReq

interface DetailProductSrv: Crud<DetailProduct, DetailProductCrtReq, DetailProductUptReq> {
    fun findByMasterProductCategoryUid(uid: String): List<DetailProduct>
}