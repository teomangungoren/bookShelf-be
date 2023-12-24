package com.microLib.library.repository

import com.microLib.library.domain.model.UserWishList
import org.springframework.data.jpa.repository.JpaRepository

interface UserWishListRepository: JpaRepository<UserWishList, String> {

    fun existsByBookIdAndUsername(bookId: String,username:String): Boolean

    fun findByBookIdAndUsername(bookId: String,username:String): UserWishList?



}