package com.microLib.library.service

import com.microLib.library.domain.converter.BookDtoConverter
import com.microLib.library.domain.response.BookResponse
import com.microLib.library.domain.request.CreateBookRequest
import com.microLib.library.repository.BookRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BookSaveService(
    private val bookRepository: BookRepository,
    private val categoryService: CategoryService
) {

    @Transactional
    fun createBook(request: CreateBookRequest): BookResponse {
        val category = categoryService.findById(request.categoryId)
        val book = BookDtoConverter.convertToBookDto(request, category!!)
        if (bookRepository.findBookByIsbn(request.isbn) != null) {
            book.count += 1
        }
        return BookResponse.convert(bookRepository.save(book))
    }

    fun deleteBook(id: String) {
        bookRepository.deleteById(id)
    }
}
