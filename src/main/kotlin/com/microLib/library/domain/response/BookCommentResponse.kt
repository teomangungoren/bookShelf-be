package com.microLib.library.domain.response

import com.microLib.library.domain.model.BookComment

data class BookCommentResponse(
    val id:String,
    val username : String,
    val comment : String,
    )
{
    companion object {
        fun convert(from: BookComment): BookCommentResponse {
            return BookCommentResponse(
                id = from.id!!,
                username = from.username,
                comment = from.comment,
            )
        }
    }
}