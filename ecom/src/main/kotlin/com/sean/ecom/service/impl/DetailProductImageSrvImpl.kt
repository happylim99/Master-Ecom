package com.sean.ecom.service.impl

import com.sean.ecom.entity.DetailProductImage
import com.sean.ecom.model.FileObj
import com.sean.ecom.repo.DetailProductImageRepo
import com.sean.ecom.repo.MasterProductRepo
import com.sean.ecom.service.BaseSrv
import com.sean.ecom.service.DetailProductImageSrv
import com.sean.ecom.util.FileUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class DetailProductImageSrvImpl(
    private val repo: DetailProductImageRepo,
    private val mpRepo: MasterProductRepo
): BaseSrv<DetailProductImage, DetailProductImageRepo>(

), DetailProductImageSrv {
    override fun createOne(file: MultipartFile, productUid: String): DetailProductImage {
        var obj = DetailProductImage()
        val fobj: List<String> = file.originalFilename!!.split(".")
        val oriName = fobj[0]
        var fileObj = FileObj(path = DetailProductImage.getBasePath(),
            oriName = oriName, ext = fobj[1], bytes = file.bytes, newName = "")
        var newName = FileUtil.handleDuplicate(fileObj)
        fileObj.newName = newName
        FileUtil.saveFile(fileObj)
        val masterProduct = mpRepo.findByUid(productUid)
        obj.path = fileObj.path
        obj.oriName = oriName
        obj.newName = newName
        obj.masterProduct = masterProduct
        return repo.save(obj);
    }

    override fun showOne(uid: String) = repo.findByNewName(uid)

    override fun deleteOne(uid: String): String {
        val theId = repo.deleteByNewName(uid)
        return if(theId.equals(0)) "not ok" else "ok"
    }

    override fun showAllValid() = customFindAll(false)

    override fun showAllVoid() = customFindAll(true)

    override fun showAll(): List<DetailProductImage> = repo.findAll()

    override fun getRepo() = repo
}