package com.microLib.library.domain.request

data class CreateCommentRequest(
    val comment:String,
    val bookId:String
)