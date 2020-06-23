package com.retail.product

import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @GetMapping("/products/{id}")
    fun getProductInfo(@PathVariable(name = "id") id: String) = productService.getProductInfo(id)

    @PutMapping("/products/{id}")
    fun updateProductInfo(@PathVariable(name = "id") id: String, @RequestBody body: String) =
        productService.updateProductInfo(id, body)


}
