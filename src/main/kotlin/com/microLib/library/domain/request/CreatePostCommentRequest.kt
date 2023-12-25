package com.microLib.library.domain.request

data class CreatePostCommentRequest(
    val postId:String,
    val comment:String
)