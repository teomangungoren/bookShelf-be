package com.microLib.library.service

import com.microLib.library.domain.model.Post
import com.microLib.library.domain.request.CreatePostRequest
import com.microLib.library.domain.request.UpdatePostRequest
import com.microLib.library.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PostService(private val postRepository: PostRepository) {

    fun createPost(request: CreatePostRequest): Post {
        val username=SecurityContextHolder.getContext().authentication.name
        with(request){
            val post=Post(postTitle,description,username,0,Instant.now(), Instant.now())
             return postRepository.save(post)
        }
    }

    fun update(request:UpdatePostRequest): Post {
        val username=SecurityContextHolder.getContext().authentication.name
        with(request){
            val post=postRepository.findPostByIdAndOwnerUsername(postId,username)
            post?.let {
                it.postTitle=postTitle
                it.description=description
                it.updatedAt=Instant.now()
                return postRepository.save(it)
            }
            throw Exception("Post not found")
        }
    }

    fun delete(postId:String){
        val username=SecurityContextHolder.getContext().authentication.name
        postRepository.findPostByIdAndOwnerUsername(postId,username)?.let {
            postRepository.delete(it)
        }
    }

    fun findById(id:String):Post{
        return postRepository.findById(id).orElseThrow { Exception("Post not found") }
    }

    fun increaseLikeCount(postId:String){
        postRepository.findByIdOrNull(postId)?.let{
            it.likes++
            postRepository.save(it)
        }
    }

    fun decreaseLikeCount(postId:String){
        postRepository.findByIdOrNull(postId)?.let{
            it.likes--
            postRepository.save(it)
        }
    }
}