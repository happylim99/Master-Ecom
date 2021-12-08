package com.sean.ecom.service.impl

import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.ecom.entity.ProductCategory
import com.sean.ecom.repo.ProductCategoryRepo
import com.sean.ecom.service.BaseSrv
import com.sean.ecom.service.ProductCategorySrv
import com.sean.ecom.ui.req.ProductCategoryCrtReq
import com.sean.ecom.ui.req.ProductCategoryUptReq
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class ProductCategorySrvImpl(
    private val repo: ProductCategoryRepo
): BaseSrv<ProductCategory, ProductCategoryRepo>(

), ProductCategorySrv {

    override fun getRepo() = repo

    override fun createOne(req: ProductCategoryCrtReq): ProductCategory {
        var obj = ProductCategory()
        BeanUtils.copyProperties(req, obj)
        obj.uid = getUUID()
        return repo.save(obj)
    }

    override fun updateOne(uid: String, req: ProductCategoryUptReq): ProductCategory {
        var dbObj = repo.findByUid(uid) ?: throw CException("Object not found")
        var obj = ProductCategory()
        BeanUtils.copyProperties(dbObj, obj, "id")
        BeanUtils.copyProperties(req, obj as Any, "id")
        repo.deleteByUid(uid)
        return repo.save(obj)
    }

    override fun showOne(uid: String) = repo.findByUid(uid)

    override fun deleteOne(uid: String): String {
        val theId = repo.deleteByUid(uid)
        return if(theId.equals(0)) "not ok" else "ok"
    }

    override fun showAllValid() = customFindAll(false)

    override fun showAllVoid() = customFindAll(true)

    override fun showAll() = findAll()
}