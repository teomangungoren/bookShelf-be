package com.microLib.library.domain.response

import com.microLib.library.domain.model.UserBook

data class UserBookResponse(
    val bookId: String,
    val username: String,
) {

    companion object{
        fun convert(from:UserBook)=UserBookResponse(
            bookId = from.bookId,
            username = from.username
        )
    }
}