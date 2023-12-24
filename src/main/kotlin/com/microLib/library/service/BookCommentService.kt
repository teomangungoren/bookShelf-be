package com.microLib.library.service

import com.microLib.library.domain.model.BookComment
import com.microLib.library.domain.request.CreateBookCommentRequest
import com.microLib.library.domain.response.BookCommentResponse
import com.microLib.library.repository.BookCommentRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class BookCommentService(private val bookCommentRepository: BookCommentRepository) {

    fun create(request: CreateBookCommentRequest): BookCommentResponse {
        with(request){
           val bookComment=BookComment(
               bookId = bookId,
               username = (SecurityContextHolder.getContext().authentication.name),
               comment = comment,
                createdDate = Instant.now()
           )
            return BookCommentResponse.convert(bookCommentRepository.save(bookComment))
        }
    }

    fun getCommentsByBookId(bookId:String):List<BookCommentResponse>{
        return bookCommentRepository.findBookCommentsByBookId(bookId)
            .map { BookCommentResponse.convert(it) }
            .toList()
    }

}