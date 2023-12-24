package com.microLib.library.service

import com.microLib.library.domain.model.Book
import com.microLib.library.domain.response.BookResponse
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.repository.BookRepository
import com.microLib.library.repository.BookSpecification
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class BookListService(
    private val bookRepository: BookRepository,
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

    fun getBooksByCategoryId(categoryId: String): List<BookResponse> {
        return bookRepository
            .findAllBooksByCategoryId(categoryId)
            .map { BookResponse.convert(it) }
            .toList()
    }

    fun search(search: String?): List<BookResponse> {
        return bookRepository
            .findAll(BookSearch().search(search))
            .map { BookResponse.convert(it) }
            .toList()
    }

    fun searchBook(search:String):List<BookResponse>{
        return BookSearch().searchBook(search).map { BookResponse.convert(it) }.toList()
    }

    private inner class BookSearch {

        fun search(search: String?): Specification<Book> = Specification { root, query, cb ->
            search?.let {
                val titlePredicate = cb.like(cb.lower(root.get<String>("author")), "%${it.lowercase()}%")
                val authorPredicate=cb.like(cb.lower(root.get<String>("title")), "%${it.lowercase()}%")
                val descriptionPredicate=cb.like(cb.lower(root.get<String>("description")), "%${it.lowercase()}%")
                cb.or(titlePredicate,authorPredicate,descriptionPredicate)
            } ?: cb.and()
        }

           fun searchBook(search:String):List<Book>{
                val specification:Specification<Book> = BookSpecification(com.microLib.library.domain.model.SearchCriteria("title",":",search.orEmpty()))
                return bookRepository.findAll(specification)
            }



    }
}
