package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_wish_list")
data class UserWishList(
    val username: String,
    val bookId: String,
):BaseEntity()