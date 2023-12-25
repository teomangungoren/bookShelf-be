package com.microLib.library.controller

import com.microLib.library.domain.request.CreateUserLikePostCommentRequest
import com.microLib.library.service.UserLikedPostCommentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/userLikedPostComments")
class UserLikedPostCommentController(private val userLikedPostCommentService: UserLikedPostCommentService){

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    fun create(request:CreateUserLikePostCommentRequest){
        userLikedPostCommentService.create(request)
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{commentId}")
    fun delete(@PathVariable commentId:String){
        userLikedPostCommentService.delete(commentId)
    }


}