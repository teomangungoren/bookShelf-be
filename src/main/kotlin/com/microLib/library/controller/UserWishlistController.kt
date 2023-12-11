package com.microLib.library.controller

import com.microLib.library.domain.model.UserWishList
import com.microLib.library.domain.request.CreateUserWishListRequest
import com.microLib.library.domain.response.UserWishListResponse
import com.microLib.library.service.UserWishListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/wishlist")
class UserWishlistController(private val userWishListService: UserWishListService) {
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/create")
    fun createUserWishList(@RequestBody request:CreateUserWishListRequest):ResponseEntity<UserWishListResponse>{
     return ResponseEntity(userWishListService.create(request),HttpStatus.CREATED)
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{username}")
    fun getAllByUsername(@PathVariable username:String):ResponseEntity<List<UserWishList>>{
        return ResponseEntity(userWishListService.getAllByUsername(username),HttpStatus.OK)
    }

}