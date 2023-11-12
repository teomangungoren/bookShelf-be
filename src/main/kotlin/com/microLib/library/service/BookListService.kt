package com.microLib.library.service

import com.microLib.library.domain.model.Book
import com.microLib.library.domain.response.BookResponse
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookListService(
    private val bookRepository: BookRepository,
    private val categoryService: CategoryService
) {



    fun listBook(): List<BookResponse> {
        return bookRepository
            .findAll()
            .map { BookResponse.convert(it) }
            .toList()
    }

    fun findByIsbn(isbn: String): BookResponse {
        val book = bookRepository.findBookByIsbn(isbn) ?: throw BookNotFoundException("Book not found with $isbn")
        return BookResponse.convert(book)
    }

    fun findById(id: String): Book {
        return bookRepository.findBookById(id) ?: throw BookNotFoundException("Book not found with $id")
    }

}
