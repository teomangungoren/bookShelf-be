package com.microLib.library.controller

import com.microLib.library.domain.model.Category
import com.microLib.library.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/wishlist")
class UserWishlistController(private val categoryService: CategoryService) {

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    fun getWishlist(@PathVariable id:String):ResponseEntity<Category>{
        return ResponseEntity(categoryService.findById(id),org.springframework.http.HttpStatus.OK)
    }
}