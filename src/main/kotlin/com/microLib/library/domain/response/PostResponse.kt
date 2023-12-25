package com.microLib.library.domain.response

import com.microLib.library.domain.model.Post

data class PostResponse(
    val postId:String,
    val postTitle:String,
    val description:String,
    val username:String,
    val likes:Int
){

    companion object{
        fun convert(from:Post):PostResponse{
            return PostResponse(
                postId = from.id!!,
                postTitle = from.postTitle,
                description = from.description,
                username = from.ownerUsername.substringBefore("@"),
                likes = from.likes
            )
        }
    }
}