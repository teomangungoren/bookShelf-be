package com.microLib.library.service

import com.microLib.library.domain.model.UserBook
import com.microLib.library.domain.request.UserBookRequest
import com.microLib.library.domain.response.UserBookResponse
import com.microLib.library.exception.BookAlreadyExistException
import com.microLib.library.repository.UserBookRepository
import org.springframework.stereotype.Service

@Service
class UserBookService(private val userBookRepository: UserBookRepository,
                      private val bookListService: BookListService) {

    fun create(request:UserBookRequest,userId:String):UserBookResponse{
        val book=bookListService.findById(request.bookId)
        checkBookExistsByBookId(book.id!!,userId)
        val userBook=userBookRepository.save(UserBook("",book.id,userId))
        return UserBookResponse.convert(userBook)
    }


    private fun checkBookExistsByBookId(bookId:String,userId:String){
        if(userBookRepository.existsByBookIdAndUserId(bookId,userId)){
            throw BookAlreadyExistException("Book already exists in user's book list")
        }
    }


}