package com.microLib.library.repository

import com.microLib.library.domain.model.PostComment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostCommentRepository : JpaRepository<PostComment, String> {
    fun findPostCommentsByPostId(postId:String):List<PostComment>

    fun findPostCommentByIdAndUsername(postId:String,username:String):PostComment?

    fun findPostCommentsByPostId(postId: String, pageable: Pageable): Page<PostComment>


}