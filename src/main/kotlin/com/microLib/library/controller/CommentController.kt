package com.microLib.library.controller

import com.microLib.library.domain.model.Comment
import com.microLib.library.domain.request.CreateCommentRequest
import com.microLib.library.service.CommentService
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
@RequestMapping("/api/v1/comments")
class CommentController(private val commentService: CommentService) {

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping()
    fun saveComment(@RequestBody request: CreateCommentRequest):ResponseEntity<*>{
        return ResponseEntity(commentService.saveComment(request),HttpStatus.CREATED)
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/comment/{bookId}")
    fun getComment(@PathVariable bookId:String):ResponseEntity<List<Comment>>{
        return ResponseEntity(commentService.getCommentsByBookId(bookId),HttpStatus.OK)
    }


}