package com.sean.ecom.controller

import com.sean.ecom.service.DetailProductImageSrv
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/productImage")
class DetailProductImageController @Autowired constructor(
    private val srv: DetailProductImageSrv
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(file: MultipartFile, productUid: String) =
        ResponseEntity.ok(srv.createOne(file, productUid))


//    @PutMapping("/{id}")
//    fun updateOne(@PathVariable id: String,
//                  @RequestBody req: ProductUptReq
//    ): ResponseEntity<Product> {
//        return ResponseEntity.ok(srv.updateOne(id, req))
//    }
//
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






