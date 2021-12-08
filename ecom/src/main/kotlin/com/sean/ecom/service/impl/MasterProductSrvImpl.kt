package com.sean.ecom.service.impl

import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.ecom.entity.MasterProduct
import com.sean.ecom.repo.ProductCategoryRepo
import com.sean.ecom.repo.MasterProductRepo
import com.sean.ecom.service.BaseSrv
import com.sean.ecom.service.MasterProductSrv
import com.sean.ecom.ui.req.CategoryProductReq
import com.sean.ecom.ui.req.ProductCrtReq
import com.sean.ecom.ui.req.ProductUptReq
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class MasterProductSrvImpl(
    private val repo: MasterProductRepo,
    private val catRepo: ProductCategoryRepo
): BaseSrv<MasterProduct, MasterProductRepo>(

), MasterProductSrv {

    override fun getRepo() = repo

    override fun createOne(req: ProductCrtReq): MasterProduct {
        var obj = MasterProduct()
        BeanUtils.copyProperties(req, obj)
        obj.uid = getUUID()
        return repo.save(obj)
    }

    override fun updateOne(uid: String, req: ProductUptReq): MasterProduct {
        var dbObj = repo.findByUid(uid) ?: throw CException("Object not found")
        var obj = MasterProduct()
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

    override fun productWantCategory(req: CategoryProductReq): MasterProduct {
        var product = repo.findByUid(req.productUid) ?: throw CException("Product not found")
        val category = catRepo.findByUid(req.categoryUid) ?: throw CException("Category not found")
        product.category!!.add(category)
        return repo.save(product)
    }

    override fun productRemoveCategory(req: CategoryProductReq): MasterProduct {
        var product = repo.findByUid(req.productUid) ?: throw CException("Product not found")
        val category = catRepo.findByUid(req.categoryUid) ?: throw CException("Category not found")
        product.category!!.remove(category)
        return repo.save(product)
    }
}