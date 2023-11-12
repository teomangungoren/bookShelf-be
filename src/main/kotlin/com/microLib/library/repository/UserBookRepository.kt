package com.microLib.library.repository

import com.microLib.library.domain.model.UserBook
import org.springframework.data.jpa.repository.JpaRepository


interface UserBookRepository: JpaRepository<UserBook, String>{

    fun existsByBookIdAndUserId(bookId: String,userId:String): Boolean
}
