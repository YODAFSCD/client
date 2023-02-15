package com.example.client.controller

import com.example.client.model.Category
import com.example.client.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/categories")

class CategoryController {
    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping
    fun list():List<Category>{
        return categoryService.list()
    }

    @PostMapping
    fun save(@RequestBody category: Category): Category?{
        return categoryService.save(category)
    }

    @PutMapping
    fun update(@RequestBody category: Category): ResponseEntity<Category> {
        return ResponseEntity(categoryService.update(category), HttpStatus.ACCEPTED)
    }

}