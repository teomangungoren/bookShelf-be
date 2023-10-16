package com.microLib.library.service

import com.microLib.library.domain.dto.BookResponse
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.repository.BookRepository

class BookListService(
    private val bookRepository: BookRepository,
    private val categoryService: CategoryService
) {

    fun listBook():List<BookResponse>{
        return bookRepository
            .findAll()
            .map { BookResponse.convert(it) }
            .toList()
    }

    fun findByIsbn(isbn:String):BookResponse{
        val book=bookRepository.findBookByIsbn(isbn)?: throw BookNotFoundException("Book not found with $isbn")
        return BookResponse.convert(book)
    }

    fun findById(id:String):BookResponse{
        val book=bookRepository.findBookById(id)?:throw BookNotFoundException("Book not found with $id")
        return BookResponse.convert(book)
    }
}