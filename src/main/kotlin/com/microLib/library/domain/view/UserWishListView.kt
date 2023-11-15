package com.microLib.library.domain.view

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Immutable
@Entity
@Table(name = "user_wish_list_view")
data class UserWishListView(
    @Id
    val id:String,
    val username:String,
    val bookId:String,
    val bookName:String,
    val firstName:String,
    val surname:String
)
