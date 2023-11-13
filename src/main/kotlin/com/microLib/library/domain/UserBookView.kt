package com.microLib.library.domain

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
    val userName:String,
    val surname:String,
)
