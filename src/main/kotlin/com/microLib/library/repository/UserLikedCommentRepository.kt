package com.microLib.library.repository

import com.microLib.library.domain.model.UserLikedComment
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikedCommentRepository : JpaRepository<UserLikedComment, String> {

    fun findUserLikedCommentByIdAndUsername(commentId: String, username: String): UserLikedComment?
}