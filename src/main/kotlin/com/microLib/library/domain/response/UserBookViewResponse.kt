package com.microLib.library.domain.response

import com.microLib.library.domain.view.UserBookView
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

data class UserBookViewResponse(
        val id: String,
        val username: String,
        val bookId: String,
        val bookName:String,
        val firstName:String,
        val surname:String
)
{
    companion object {
        @JvmStatic
        fun convert(from:UserBookView): UserBookViewResponse {
            return UserBookViewResponse(
                    id = from.id,
                    username = from.username.substringBefore("@"),
                    bookId = from.bookId,
                    bookName = from.bookName,
                    firstName = from.firstName,
                    surname = from.surname
            )
        }
    }
}


