package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_book")
data class UserBook(
    val username: String,
    val bookId: String,
): BaseEntity()