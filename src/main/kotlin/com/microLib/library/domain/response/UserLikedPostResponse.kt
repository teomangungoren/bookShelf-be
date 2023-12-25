package com.microLib.library.domain.response

import java.time.Instant

data class UserLikedPostResponse(
    val id:String,
    val username:String,
    val postId:String,
    val createdAt:Instant
)
