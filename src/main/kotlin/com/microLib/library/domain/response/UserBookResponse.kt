package com.microLib.library.domain.response

import com.microLib.library.domain.model.UserBook

data class UserBookResponse(
    val bookId: String,
    val userId: String,
) {

    companion object{
        fun convert(from:UserBook)=UserBookResponse(
            bookId = from.bookId,
            userId = from.userId
        )
    }
}