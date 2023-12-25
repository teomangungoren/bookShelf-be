package com.microLib.library.domain.response

import com.microLib.library.domain.model.UserWishList

data class UserWishListResponse(
    val bookId: String,
    val username: String
) {
    companion object{
        fun convert(from: UserWishList): UserWishListResponse {
            return UserWishListResponse(
                bookId = from.bookId,
                username = from.username
            )
        }
    }
}