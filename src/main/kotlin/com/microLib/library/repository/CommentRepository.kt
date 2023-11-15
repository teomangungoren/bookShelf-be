package com.microLib.library.repository

import com.microLib.library.domain.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository:JpaRepository<Comment,String> {

    fun findCommentsByBookId(bookId:String):List<Comment>?
}