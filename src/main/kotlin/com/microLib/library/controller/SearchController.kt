package com.microLib.library.controller

import com.microLib.library.domain.response.BookResponse
import com.microLib.library.service.SearchService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
@CrossOrigin("*")
class SearchController(private val searchService: SearchService){

    @GetMapping
     fun searchBooks(@RequestParam search:String?):ResponseEntity<List<BookResponse>>{
        return ResponseEntity(searchService.search(search),HttpStatus.OK)
    }

}