package com.microLib.library.service

import com.microLib.library.domain.model.UserLikedBookComment
import com.microLib.library.domain.request.CreateUserLikedBookCommentRequest
import com.microLib.library.repository.UserLikedBookCommentRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class UserLikedBookCommentService(
    private val userLikedBookCommentRepository: UserLikedBookCommentRepository,
    private val bookCommentService: BookCommentService){

    @Transactional
    fun create(request: CreateUserLikedBookCommentRequest){
        try{
        val username= SecurityContextHolder.getContext().authentication.name
        with(request){
            val userLikedComment= UserLikedBookComment(bookCommentId,username, Instant.now(), Instant.now())
            userLikedBookCommentRepository.save(userLikedComment)
        }
        bookCommentService.findById(request.bookCommentId)?.let {
             bookCommentService.increaseLikeCount(it.id!!)
        }}catch (e:Exception){
            println(e)
        }
    }

    fun delete(request: CreateUserLikedBookCommentRequest){
        val username= SecurityContextHolder.getContext().authentication.name
        userLikedBookCommentRepository.findUserLikedCommentByIdAndUsername(request.bookCommentId,username)?.let {
            userLikedBookCommentRepository.delete(it)
            bookCommentService.decreaseLikeCount(it.bookCommentId)
        }
    }


}