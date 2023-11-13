package com.microLib.library.repository

import com.microLib.library.domain.UserBookView
import org.springframework.data.jpa.repository.JpaRepository

interface UserBookViewRepository:JpaRepository<UserBookView,String> {

    fun getUsersByBookId(bookId: String): List<UserBookView>?

}