package com.microLib.library.repository

import com.microLib.library.domain.model.UserLikedPostComment
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikedCommentRepository : JpaRepository<UserLikedPostComment, String> {

    fun findUserLikedCommentByIdAndUsername(commentId: String, username: String): UserLikedPostComment?
}