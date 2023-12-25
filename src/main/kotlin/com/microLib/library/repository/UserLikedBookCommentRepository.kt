package com.microLib.library.repository

import com.microLib.library.domain.model.UserLikedBookComment
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikedBookCommentRepository : JpaRepository<UserLikedBookComment,String>  {

    fun findUserLikedCommentByIdAndUsername(commentId:String,username:String):UserLikedBookComment?
}