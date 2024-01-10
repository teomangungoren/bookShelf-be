package com.microLib.library.repository

import com.microLib.library.domain.model.UserBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface UserBookRepository: JpaRepository<UserBook, String>{

    fun existsByBookIdAndUsername(bookId: String,username:String): Boolean

    fun findByUsername(username: String): List<UserBook>?

    fun findByBookId(bookId: String): List<UserBook>?

    fun findByBookIdAndUsername(bookId: String,username: String): UserBook?

    @Query("SELECT u.bookId FROM UserBook u GROUP BY u.bookId ORDER BY COUNT(u.bookId) DESC")
    fun getAllBooksOrderByOwnerShipDesc():List<String>
}
