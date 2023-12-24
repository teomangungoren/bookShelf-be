package com.microLib.library.service

import com.microLib.library.domain.response.BookResponse
import org.springframework.stereotype.Service

@Service
class SearchService(private val bookListService: BookListService) {
   fun search(search: String?): List<BookResponse> {
        return bookListService.search(search)
     }
}