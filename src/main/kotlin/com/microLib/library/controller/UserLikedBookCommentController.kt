package com.microLib.library.controller

import com.microLib.library.domain.request.CreateUserLikedBookCommentRequest
import com.microLib.library.service.UserLikedBookCommentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user-liked-book-comment")
class UserLikedBookCommentController(private val userLikedBookCommentService: UserLikedBookCommentService) {

    @PostMapping
    fun create(request: CreateUserLikedBookCommentRequest){
        userLikedBookCommentService.create(request)
    }

    @DeleteMapping("/{commentId}")
    fun delete(@PathVariable commentId:String){
        userLikedBookCommentService.delete(commentId)
    }
}