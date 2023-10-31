package com.microLib.library.repository

import com.microLib.library.domain.model.UserWishList
import org.springframework.data.jpa.repository.JpaRepository

interface UserWishListRepository: JpaRepository<UserWishList, String> {

    fun existsByUserIdAndBookId(userId: String, bookId: String): Boolean

    fun findBookByUserIdAndBookId(userId: String,bookId:String): UserWishList?
}