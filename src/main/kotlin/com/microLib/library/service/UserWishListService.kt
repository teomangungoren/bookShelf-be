package com.microLib.library.service

import com.microLib.library.domain.request.CreateUserWishListRequest
import com.microLib.library.domain.model.UserWishList
import com.microLib.library.domain.response.UserWishListResponse
import com.microLib.library.exception.BookAlreadyExistException
import com.microLib.library.repository.UserWishListRepository
import org.springframework.stereotype.Service

@Service
class UserWishListService(private val userWishListRepository: UserWishListRepository,
                          private val bookListService: BookListService) {

    fun create(request: CreateUserWishListRequest):UserWishListResponse{
        val book=bookListService.findById(request.bookId)
        checkBookExistsByUser(request.userId,request.bookId)
        val userWishList=userWishListRepository.save(UserWishList(
            bookId = book.id!!,
            userId = request.userId))
        return UserWishListResponse.convert(userWishList)
    }


    private fun checkBookExistsByUser(userId:String,bookId:String){
        if(userWishListRepository.existsByUserIdAndBookId(userId,bookId)){
            throw BookAlreadyExistException("Book already exists in user's wish list")
        }
    }
}