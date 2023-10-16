package com.microLib.library.repository

import com.microLib.library.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository


interface BookRepository:JpaRepository<Book, String> {
}