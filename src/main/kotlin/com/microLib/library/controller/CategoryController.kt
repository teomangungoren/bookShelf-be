package com.microLib.library.controller

import com.microLib.library.domain.model.Category
import com.microLib.library.domain.request.CreateCategoryRequest
import com.microLib.library.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(private val categoryService: CategoryService) {

    @RequestMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    fun createCategory(@RequestBody createCategoryRequest: CreateCategoryRequest):ResponseEntity<*> {
       return ResponseEntity(categoryService.create(createCategoryRequest),HttpStatus.CREATED)
    }

    @RequestMapping("/category")
    @PreAuthorize("hasAuthority('USER')")
    fun getCategoryById(@RequestParam(name = "id") id:String):ResponseEntity<Category>{
        return ResponseEntity(categoryService.findById(id),HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/categories")
    fun getCategories():ResponseEntity<List<Category>>{
        return ResponseEntity(categoryService.getAll(),HttpStatus.OK)
    }


}