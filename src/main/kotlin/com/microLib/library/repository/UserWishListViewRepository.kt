package com.microLib.library.repository

import com.microLib.library.domain.view.UserBookView
import com.microLib.library.domain.view.UserWishListView
import org.springframework.data.jpa.repository.JpaRepository

interface UserWishListViewRepository:JpaRepository<UserWishListView,String> {

    fun getAllUsersByBookId(bookId:String):List<UserWishListView>?

    fun getAllBooksByUsername(username:String):List<UserWishListView>?

}