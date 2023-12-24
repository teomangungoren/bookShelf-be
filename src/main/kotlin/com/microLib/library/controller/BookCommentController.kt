package com.microLib.library.controller

import com.microLib.library.domain.model.BookComment
import com.microLib.library.domain.request.CreateBookCommentRequest
import com.microLib.library.domain.response.BookCommentResponse
import com.microLib.library.service.BookCommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/bookComments")
class BookCommentController(private val bookCommentService: BookCommentService) {

    @PostMapping("/create")
    fun create(@RequestBody request: CreateBookCommentRequest): ResponseEntity<BookCommentResponse> {
        return ResponseEntity(bookCommentService.create(request), HttpStatus.CREATED)
    }

    @GetMapping()
    fun getCommentsByBookId(@RequestParam bookId: String): ResponseEntity<List<BookCommentResponse>> {
        return ResponseEntity(bookCommentService.getCommentsByBookId(bookId), HttpStatus.OK)
    }

}