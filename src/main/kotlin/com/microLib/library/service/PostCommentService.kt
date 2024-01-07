package com.microLib.library.service

import com.microLib.library.domain.model.PostComment
import com.microLib.library.domain.request.CreatePostCommentRequest
import com.microLib.library.domain.response.PostCommentResponse
import com.microLib.library.pagination.PaginationRequest
import com.microLib.library.pagination.PaginationResponse
import com.microLib.library.repository.PostCommentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PostCommentService(private val postCommentRepository: PostCommentRepository,private val postService: PostService) {

    fun create(request:CreatePostCommentRequest):PostComment{
        val username= SecurityContextHolder.getContext().authentication.name
        postService.findById(request.postId)
            with(request){
                val postComment=PostComment(postId,username,postId,0, Instant.now(), Instant.now())
                return postCommentRepository.save(postComment)
            }
        }

     fun getCommentsByPostId(postId:String):List<PostComment>{
         return postCommentRepository.findPostCommentsByPostId(postId)
     }

     fun deleteComment(postId:String){
         val username=SecurityContextHolder.getContext().authentication.name
         postCommentRepository.findPostCommentsByPostId(postId).find { it.username==username }?.let {
             postCommentRepository.delete(it)
         }
     }

    fun findById(id:String): PostComment {
        return postCommentRepository.findById(id).orElseThrow { Exception("Post not found") }
    }

    fun increaseLikeCount(postId:String){
        postCommentRepository.findByIdOrNull(postId)?.let{
            it.likes++
            postCommentRepository.save(it)
        }
    }

    fun decreaseLikeCount(postId:String){
        postCommentRepository.findByIdOrNull(postId)?.let{
            it.likes--
            postCommentRepository.save(it)
        }
    }

    fun getCommentsByPostIdWithPagination(postId:String,request:PaginationRequest):PaginationResponse<PostCommentResponse>{
        val pageRequest=PageRequest.of(request.page-1,request.size)
        val page:Page<PostComment> = postCommentRepository.findPostCommentsByPostId(postId,pageRequest)

        return PaginationResponse(
            page.content.map { PostCommentResponse(it.id!!,it.username,it.comment,Instant.now()) },
            page.totalPages,
            page.totalElements,
            page.number+1
        )
    }



}