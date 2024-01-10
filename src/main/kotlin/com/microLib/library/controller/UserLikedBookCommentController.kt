package com.microLib.library.controller

import com.microLib.library.domain.request.CreateUserLikedBookCommentRequest
import com.microLib.library.service.UserLikedBookCommentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user-liked-book-comment")
    class UserLikedBookCommentController(private val userLikedBookCommentService: UserLikedBookCommentService) {

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/create")
    fun create(request: CreateUserLikedBookCommentRequest){
        userLikedBookCommentService.create(request)
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/delete")
    fun delete(request: CreateUserLikedBookCommentRequest){
        userLikedBookCommentService.delete(request)
    }
}