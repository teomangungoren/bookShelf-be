package com.microLib.library.repository

import com.microLib.library.domain.model.PostComment
import org.springframework.data.jpa.repository.JpaRepository

interface PostCommentRepository : JpaRepository<PostComment, String> {
    fun findPostCommentsByPostId(postId:String):List<PostComment>

    fun findPostCommentByIdAndUsername(postId:String,username:String):PostComment?

}