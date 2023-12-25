package com.microLib.library.controller

import com.microLib.library.domain.request.CreatePostCommentRequest
import com.microLib.library.domain.response.PostCommentResponse
import com.microLib.library.service.PostCommentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/postComments")
class PostCommentController(private val postCommentService: PostCommentService) {

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/create")
    fun createComment(@RequestBody request:CreatePostCommentRequest):PostCommentResponse{
        return PostCommentResponse.convert(postCommentService.create(request))
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{postId}")
    fun getCommentsByPostId(@PathVariable postId:String):List<PostCommentResponse>{
        return postCommentService.getCommentsByPostId(postId)
            .map { PostCommentResponse.convert(it) }
            .toList()
    }

    @DeleteMapping("/{postId}")
    fun deleteComment(@PathVariable postId:String){
        postCommentService.deleteComment(postId)
    }



}