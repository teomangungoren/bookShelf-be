package com.microLib.library.service

import com.microLib.library.domain.request.UserBookRequest
import com.microLib.library.domain.response.UserBookResponse
import com.microLib.library.repository.UserBookRepository
import org.springframework.stereotype.Service

@Service
class UserBookService(private val userBookRepository: UserBookRepository) {

   /* fun create(request:UserBookRequest):UserBookResponse{

    }

    private fun checkBookExistsByUser(userId:String,bookId:String){
        if(userBookRepository.existsByUserIdAndBookId(userId,bookId)){
            throw BookAlreadyExistException("Book already exists in user's wish list")
        }
    }
    */

}