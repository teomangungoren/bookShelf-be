package com.microLib.library.repository

import com.microLib.library.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, String> {

    fun findBookByIsbn(isbn: String): Book?

    fun findBookById(id: String): Book?
}
