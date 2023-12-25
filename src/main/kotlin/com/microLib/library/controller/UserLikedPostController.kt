package com.microLib.library.controller

import com.microLib.library.domain.request.CreateUserLikedPostRequest
import com.microLib.library.service.UserLikedPostService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/userLikedPosts")
class UserLikedPostController(private val userLikedPostService: UserLikedPostService) {

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    fun create(@RequestBody request:CreateUserLikedPostRequest){
        userLikedPostService.create(request)
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/{postId}")
    fun delete(@PathVariable postId:String){
        userLikedPostService.delete(postId)
    }

}