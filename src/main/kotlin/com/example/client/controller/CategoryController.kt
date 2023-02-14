package com.example.client.controller


import com.example.client.model.Product

import com.example.client.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list():List<Product>{
        return productService.list()
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product?{
        return productService.save(product)

    }

    @PutMapping
    fun update(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(product), HttpStatus.ACCEPTED)
    }



    @PatchMapping
    fun listById(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateTotal(product), HttpStatus.ACCEPTED)
    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return productService.delete(id)
    }

}