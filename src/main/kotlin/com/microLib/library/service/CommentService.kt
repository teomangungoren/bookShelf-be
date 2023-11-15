package com.microLib.library.service

import com.microLib.library.domain.model.Comment
import com.microLib.library.domain.request.CreateCommentRequest
import com.microLib.library.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository) {

    fun saveComment(request:CreateCommentRequest)=commentRepository.save(Comment(comment =request.comment,bookId = request.bookId ))

    fun getCommentsByBookId(bookId:String)=commentRepository.findCommentsByBookId(bookId)

}