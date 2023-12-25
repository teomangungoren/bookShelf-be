package com.microLib.library.repository

import com.microLib.library.domain.model.UserLikedPost
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikedPostRepository : JpaRepository<UserLikedPost, String> {

    fun findUserLikedPostByPostIdAndUsername(postId:String,username:String):UserLikedPost?
}