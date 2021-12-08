package com.sean.ecom.controller

import com.sean.ecom.entity.MasterProduct
import com.sean.ecom.service.MasterProductSrv
import com.sean.ecom.ui.req.CategoryProductReq
import com.sean.ecom.ui.req.ProductCrtReq
import com.sean.ecom.ui.req.ProductUptReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
//@CrossOrigin(origins = ["http://localhost:4200", "http://someserver:8080"])
@RequestMapping("/masterProduct")
class MasterProductController @Autowired constructor(
    private val srv: MasterProductSrv
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(@RequestBody req: ProductCrtReq) =
        ResponseEntity.ok(srv.createOne(req))

    @PutMapping("/{id}")
    fun updateOne(@PathVariable id: String,
                  @RequestBody req: ProductUptReq
    ): ResponseEntity<MasterProduct> {
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

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = ResponseEntity.ok(srv.deleteOne(id))

    @GetMapping("/productWantCategory")
    fun productWantCategory(@RequestBody req: CategoryProductReq) =
        ResponseEntity.ok(srv.productWantCategory(req))

    @GetMapping("/productRemoveCategory")
    fun productRemoveCategory(@RequestBody req: CategoryProductReq) =
        ResponseEntity.ok(srv.productRemoveCategory(req))
}






