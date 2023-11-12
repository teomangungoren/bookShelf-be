package com.microLib.library.controller

import com.microLib.library.config.security.AuthResolver
import com.microLib.library.domain.model.UserBook
import com.microLib.library.domain.request.UserBookRequest
import com.microLib.library.domain.response.UserBookResponse
import com.microLib.library.service.UserBookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/books")
class UserBookController(private val userBookService: UserBookService):AuthResolver(){

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/create")
    fun createUserBook(request:UserBookRequest):ResponseEntity<UserBookResponse>{
       return withAuth{ResponseEntity(userBookService.create(request,it.getUserId()), HttpStatus.CREATED) }
    }

}