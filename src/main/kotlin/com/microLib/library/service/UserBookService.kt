package com.microLib.library.service

import com.microLib.library.domain.model.UserBook
import com.microLib.library.domain.request.UserBookRequest
import com.microLib.library.domain.response.UserBookResponse
import com.microLib.library.exception.BookAlreadyExistException
import com.microLib.library.repository.UserBookRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserBookService(private val userBookRepository: UserBookRepository,
                      private val bookListService: BookListService,) {

    fun create(request:UserBookRequest):UserBookResponse{
        val email=SecurityContextHolder.getContext().authentication.name
        val book=bookListService.findById(request.bookId)
        checkBookExistsByBookId(book.id!!,email)
        val userBook=userBookRepository.save(UserBook("",book.id,email))
        return UserBookResponse.convert(userBook)
    }

    fun getAllByUsername(username:String):List<UserBook>{
        return userBookRepository.findByUsername(username)?:throw IllegalArgumentException("User not found")
    }




    private fun checkBookExistsByBookId(bookId:String,username:String){
        require(!userBookRepository.existsByBookIdAndUsername(bookId,username)){
            throw BookAlreadyExistException("Book already exists in user's book list")
        }
    }


}