package com.microLib.library.repository

import com.microLib.library.domain.model.UserWishList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserWishListRepository: JpaRepository<UserWishList, String> {

    fun existsByBookIdAndUsername(bookId: String,username:String): Boolean

    fun findByBookIdAndUsername(bookId: String,username:String): UserWishList?

    @Query("SELECT u.bookId FROM UserWishList u GROUP BY u.bookId ORDER BY COUNT(u.bookId) DESC")
    fun getAllBooksOrderByOwnerShipDesc():List<String>


}