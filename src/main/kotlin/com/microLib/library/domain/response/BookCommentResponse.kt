package com.microLib.library.domain.response

import com.microLib.library.domain.model.BookComment

data class BookCommentResponse(
    val username : String,
    val comment : String,
    )
{
    companion object {
        fun convert(from: BookComment): BookCommentResponse {
            return BookCommentResponse(
                username = from.username,
                comment = from.comment,
            )
        }
    }
}