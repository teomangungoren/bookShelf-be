package com.microLib.library.controller

import com.microLib.library.domain.model.Book
import com.microLib.library.domain.response.BookResponse
import com.microLib.library.domain.request.CreateBookRequest
import com.microLib.library.domain.request.UpdateBookRequest
import com.microLib.library.service.BookListService
import com.microLib.library.service.BookSaveService
import com.microLib.library.service.BookUpdateService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
class BookController(
    private val bookListService: BookListService,
    private val bookSaveService: BookSaveService,
    private val bookUpdateService: BookUpdateService
) {
    private val logger = LoggerFactory.getLogger(BookController::class.java)

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    fun saveBook(@RequestBody createBookRequest: CreateBookRequest): ResponseEntity<BookResponse> {
        logger.info("BookController.saveBook() called with: createBookRequest = [$createBookRequest]")
        return ResponseEntity.status(HttpStatus.CREATED).body(bookSaveService.saveBook(createBookRequest))
    }
    @GetMapping
    fun getBookList(): ResponseEntity<List<BookResponse>> {
        return ResponseEntity(bookListService.listBook(), HttpStatus.OK)
    }

    @GetMapping("/book/isbn/{isbn}")
    fun getBookByIsbn(@PathVariable isbn: String): ResponseEntity<BookResponse> {
        return ResponseEntity(bookListService.findByIsbn(isbn), HttpStatus.OK)
    }

    @GetMapping("/book/category/{categoryId}")
    fun getBooksByCategoryId(@PathVariable categoryId: String): ResponseEntity<List<BookResponse>> {
        return ResponseEntity(bookListService.getBooksByCategoryId(categoryId), HttpStatus.OK)
    }

    @GetMapping("/book/id/{id}")
    fun getBookById(@PathVariable id: String): ResponseEntity<BookResponse> {
        return ResponseEntity(BookResponse.convert(bookListService.findById(id)), HttpStatus.OK)
    }

    @PutMapping("/update")
    fun updateBook(@RequestBody updateBookRequest: UpdateBookRequest): ResponseEntity<BookResponse> {
        return ResponseEntity(bookUpdateService.updateBook(updateBookRequest), HttpStatus.OK)
    }

    @GetMapping("/search")
    fun search(@RequestParam search: String?): ResponseEntity<List<BookResponse>> {
        return ResponseEntity(bookListService.search(search), HttpStatus.OK)
    }

    @GetMapping("/search2")
    fun searchBook(@RequestParam search:String):ResponseEntity<List<BookResponse>>{
        return ResponseEntity(bookListService.searchBook(search),HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('Admin')")
    fun deleteBook(id:String)=bookSaveService.deleteBook(id)


}
