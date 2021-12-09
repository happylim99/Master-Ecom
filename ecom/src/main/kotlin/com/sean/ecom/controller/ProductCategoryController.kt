package com.sean.ecom.controller

import com.sean.ecom.entity.ProductCategory
import com.sean.ecom.service.ProductCategorySrv
import com.sean.ecom.ui.req.ProductCategoryCrtReq
import com.sean.ecom.ui.req.ProductCategoryUptReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/productCategory")
class ProductCategoryController @Autowired constructor(
    private val srv: ProductCategorySrv
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(@RequestBody req: ProductCategoryCrtReq): ResponseEntity<ProductCategory> {
        return ResponseEntity.ok(srv.createOne(req))
    }

    @PutMapping("/{uid}")
    fun updateOne(@PathVariable uid: String,
                  @RequestBody req: ProductCategoryUptReq): ResponseEntity<ProductCategory> {
        return ResponseEntity.ok(srv.updateOne(uid, req))
    }

    @GetMapping("/{id}")
    fun showOne(@PathVariable id: String) = ResponseEntity.ok(srv.showOne(id))

    @GetMapping("/showAllValid")
    fun showAllValid() = ResponseEntity.ok(srv.showAllValid())

    @GetMapping("/showAllVoid")
    fun showAllVoid() = ResponseEntity.ok(srv.showAllVoid())

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = ResponseEntity.ok(srv.deleteOne(id))
}