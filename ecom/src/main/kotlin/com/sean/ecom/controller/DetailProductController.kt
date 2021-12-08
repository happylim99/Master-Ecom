package com.sean.ecom.controller

import com.sean.ecom.entity.DetailProduct
import com.sean.ecom.service.DetailProductSrv
import com.sean.ecom.service.impl.DetailProductSrvImpl
import com.sean.ecom.ui.req.DetailProductCrtReq
import com.sean.ecom.ui.req.DetailProductUptReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/detailProduct")
class DetailProductController @Autowired constructor(
    private val srv: DetailProductSrv
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(@RequestBody req: DetailProductCrtReq): ResponseEntity<DetailProduct> {
        return ResponseEntity.ok(srv.createOne(req))
    }

    @PutMapping("/{id}")
    fun updateOne(@PathVariable id: String,
                  @RequestBody req: DetailProductUptReq
    ): ResponseEntity<DetailProduct> {
        return ResponseEntity.ok(srv.updateOne(id, req))
    }

    @GetMapping("/{id}")
    fun showOne(@PathVariable id: String) = ResponseEntity.ok(srv.showOne(id))

    @GetMapping("/showAllValid")
    fun showAllValid() = ResponseEntity.ok(srv.showAllValid())

    @GetMapping("/showAllVoid")
    fun showAllVoid() = ResponseEntity.ok(srv.showAllVoid())

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{uid}")
    fun deleteOne(@PathVariable uid: String) = ResponseEntity.ok(srv.deleteOne(uid))

    @GetMapping("/findByMasterProductCategoryUid/{uid}")
    fun findByMasterProductCategoryUid(@PathVariable uid: String) =
        ResponseEntity.ok(srv.findByMasterProductCategoryUid(uid))

}