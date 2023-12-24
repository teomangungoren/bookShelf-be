package com.microLib.library.repository

import com.microLib.library.domain.model.BookComment
import org.springframework.data.jpa.repository.JpaRepository

interface BookCommentRepository:JpaRepository<BookComment,String> {

    fun findBookCommentsByBookId(bookId:String):List<BookComment>
}