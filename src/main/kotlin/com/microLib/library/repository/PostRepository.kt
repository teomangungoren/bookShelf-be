package com.microLib.library.repository

import com.microLib.library.domain.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, String> {

    fun findPostByIdAndOwnerUsername(postId:String,ownerUsername:String):Post?

    fun findPostById(postId:String):Post?
}