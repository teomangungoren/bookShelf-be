package com.microLib.library.domain.response

import com.microLib.library.domain.model.PostComment
import java.time.Instant

data class PostCommentResponse(
    val id:String,
    val username:String,
    val comment:String,
    val createdAt:Instant
){
    companion object{
        fun convert(from:PostComment):PostCommentResponse{
            return PostCommentResponse(
                id = from.id!!,
                username = from.username.substringBefore("@"),
                comment = from.comment,
                createdAt = from.createdAt!!
            )
        }
    }
}

