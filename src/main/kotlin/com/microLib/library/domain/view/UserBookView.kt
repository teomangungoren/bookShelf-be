package com.microLib.library.domain.view

import com.microLib.library.domain.model.User
import com.microLib.library.domain.model.UserBook
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Immutable
@Entity
@Table(name = "user_book_view")
data class UserBookView(
    @Id
    val id: String,
    val username: String,
    val bookId: String,
    val bookName:String,
    val firstName:String,
    val surname:String,
)
