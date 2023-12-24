package com.microLib.library.domain.request

data class CreateBookCommentRequest(
    val bookId:String,
    val comment:String
)