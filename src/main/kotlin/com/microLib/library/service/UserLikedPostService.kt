package com.microLib.library.service

import com.microLib.library.domain.model.UserLikedPost
import com.microLib.library.domain.request.CreateUserLikedPostRequest
import com.microLib.library.repository.UserLikedPostRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class UserLikedPostService(private val userLikedPostRepository: UserLikedPostRepository,
                           private val postService: PostService) {

    @Transactional
    fun create(request:CreateUserLikedPostRequest){
       val username= SecurityContextHolder.getContext().authentication.name
        with(request){
          val userLikedPost=UserLikedPost(username,postId, Instant.now(), Instant.now())
            userLikedPostRepository.save(userLikedPost)
        }
        postService.findById(request.postId)?.let {
            postService.increaseLikeCount(it.id!!)
        }
    }

    fun delete(postId:String){
        val username=SecurityContextHolder.getContext().authentication.name
        userLikedPostRepository.findUserLikedPostByPostIdAndUsername(postId,username)?.let {
            userLikedPostRepository.delete(it)
            postService.decreaseLikeCount(postId)
        }
    }
}