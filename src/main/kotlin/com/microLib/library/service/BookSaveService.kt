package com.microLib.library.service

import com.microLib.library.domain.converter.BookDtoConverter
import com.microLib.library.domain.dto.BookResponse
import com.microLib.library.domain.dto.CreateBookRequest
import com.microLib.library.repository.BookRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BookSaveService(private val bookRepository:BookRepository,
    private val categoryService: CategoryService)   {

    @Transactional
    fun createBook(createBookRequest: CreateBookRequest):BookResponse{
        val category=categoryService.findById(createBookRequest.categoryId)
        val book=BookDtoConverter.convertToBookDto(createBookRequest,category!!)
        if(bookRepository.findBookByIsbn(createBookRequest.isbn)!=null){
            book.count+=1
        }
        return BookResponse.convert(bookRepository.save(book))
    }

    fun deleteBook(id:String){
        bookRepository.deleteById(id)
    }

}