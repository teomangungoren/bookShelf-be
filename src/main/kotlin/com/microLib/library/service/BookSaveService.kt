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
    fun saveBook(request: CreateBookRequest): BookResponse {
        val category = categoryService.findById(request.categoryId)
        val book = BookDtoConverter.convertToBookDto(request, category!!)
        return BookResponse.convert(bookRepository.save(book))
    }

    fun deleteBook(id: String) {
        bookRepository.deleteById(id)
    }

    fun calculateRating(bookId: String, rating: Int) {
        val book = bookRepository.findById(bookId).get()
        book.totalOwner++
        book.apply {
            totalRating= (((totalRating * totalOwner + rating) / (totalOwner+1)) * 1.0).toFloat()
            totalOwner++
        }
        bookRepository.save(book)
    }

    fun increaseTotalOwner(bookId: String) {
        val book = bookRepository.findBookById(bookId)?.let {
            it.totalOwner++
            bookRepository.save(it)
        }
    }

    fun increaseTotalWishlistOwner(bookId: String) {
        val book = bookRepository.findBookById(bookId)?.let {
            it.totalWishlistOwner = it.totalWishlistOwner!! + 1
            bookRepository.save(it)
        }
    }


}
