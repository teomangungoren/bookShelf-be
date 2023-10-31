package com.microLib.library.domain.request


data class CreateUserWishListRequest(
    val bookId:String,
    val userId:String
)