package com.microLib.library.repository

import com.microLib.library.domain.view.UserBookView
import org.springframework.data.jpa.repository.JpaRepository

interface UserBookViewRepository:JpaRepository<UserBookView,String>{

    fun getAllUsersByBookId(bookId:String):List<UserBookView>?

    fun getAllBooksByUsername(username:String):List<UserBookView>?

}