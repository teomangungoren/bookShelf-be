package com.microLib.library.repository

import com.microLib.library.domain.model.UserBook
import org.springframework.data.jpa.repository.JpaRepository


interface UserBookRepository: JpaRepository<UserBook, String>{

    fun existsByBookIdAndUsername(bookId: String,username:String): Boolean

    fun findByUsername(username: String): List<UserBook>?

    fun findByBookId(bookId: String): List<UserBook>?
}
