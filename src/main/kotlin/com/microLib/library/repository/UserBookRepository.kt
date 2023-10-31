package com.microLib.library.repository

import com.microLib.library.domain.model.UserBook
import org.springframework.data.jpa.repository.JpaRepository


interface UserBookRepository: JpaRepository<UserBook, String>{

    fun existsByBookId(bookId: String): Boolean
}
