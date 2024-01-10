package com.microLib.library.controller

import com.microLib.library.domain.request.CreatePostRequest
import com.microLib.library.domain.request.UpdatePostRequest
import com.microLib.library.domain.response.PostResponse
import com.microLib.library.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/posts")
class PostController(private val postService: PostService){

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/create")
    fun createPost(@RequestBody request:CreatePostRequest):ResponseEntity<PostResponse>{
        return ResponseEntity(PostResponse.convert(postService.createPost(request)),HttpStatus.CREATED)
    }

    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/update")
    fun updatePost(@RequestBody request:UpdatePostRequest):ResponseEntity<PostResponse>{
        return ResponseEntity(PostResponse.convert(postService.update(request)),HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId:String){
        postService.delete(postId)
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId:String):ResponseEntity<PostResponse>{
        return ResponseEntity(PostResponse.convert(postService.findById(postId)),HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/all")
    fun getAll():ResponseEntity<List<PostResponse>>{
        return ResponseEntity(postService.getAll().map { PostResponse.convert(it) },HttpStatus.OK)
    }


}