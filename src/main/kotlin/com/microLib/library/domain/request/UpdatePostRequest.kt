package com.microLib.library.domain.request

data class UpdatePostRequest(
    val postId:String,
    val postTitle:String,
    val description:String
)