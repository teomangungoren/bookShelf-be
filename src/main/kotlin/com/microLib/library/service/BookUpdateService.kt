package com.microLib.library.service

import com.microLib.library.domain.response.BookResponse
import com.microLib.library.domain.request.UpdateBookRequest
import com.microLib.library.domain.model.Book
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.extension.General
import com.microLib.library.repository.BookRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BookUpdateService(
    private val bookRepository: BookRepository,
    private val general: General
) {

    @Transactional(rollbackOn = [Exception::class])
    fun updateBook(updateBookRequest: UpdateBookRequest): BookResponse {
        val book = bookRepository.findBookById(updateBookRequest.id) ?: throw BookNotFoundException("Book not found with ${updateBookRequest.id}")
        updateAttributes(updateBookRequest, book)
        return BookResponse.convert(bookRepository.save(book))
    }

    private fun updateAttributes(updateRequest: UpdateBookRequest, book: Book) {
        with(updateRequest) {
            book.title = title ?: book.title
            book.author = author ?: book.author
            book.bookYear = bookYear ?: book.bookYear
            book.pressYear = pressYear ?: book.pressYear
            book.totalPageNumber = totalPageNumber ?: book.totalPageNumber
        }
    }
}
