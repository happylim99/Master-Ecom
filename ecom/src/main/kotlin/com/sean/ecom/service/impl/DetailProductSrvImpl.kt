package com.sean.ecom.service.impl

import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.ecom.entity.DetailProduct
import com.sean.ecom.entity.ProductCategory
import com.sean.ecom.repo.MasterProductRepo
import com.sean.ecom.repo.DetailProductRepo
import com.sean.ecom.repo.ProductCategoryRepo
import com.sean.ecom.service.BaseSrv
import com.sean.ecom.service.DetailProductSrv
import com.sean.ecom.ui.req.DetailProductCrtReq
import com.sean.ecom.ui.req.DetailProductUptReq
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class DetailProductSrvImpl(
    private val repo: DetailProductRepo,
    private val productRepo: MasterProductRepo,
    private val catRepo: ProductCategoryRepo
): BaseSrv<DetailProduct, DetailProductRepo>(

), DetailProductSrv {

    override fun getRepo() = repo

    override fun createOne(req: DetailProductCrtReq): DetailProduct {
        var obj = DetailProduct()
        val masterProduct = req.productUid?.let {
            productRepo.findByUid(req.productUid!!) ?: throw CException("Master product not found")
        }
        BeanUtils.copyProperties(req, obj)
        obj.uid = getUUID()
        obj.masterProduct = masterProduct
        return repo.save(obj)
    }

    override fun updateOne(uid: String, req: DetailProductUptReq): DetailProduct {
        var dbObj = repo.findByUid(uid) ?: throw CException("Object not found")
        var obj = DetailProduct()
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

    override fun findByMasterProductCategoryUid(uid: String): List<DetailProduct> =
        repo.findByMasterProductCategoryUid(uid)

}