package com.microLib.library.service

import com.microLib.library.domain.request.CreateUserWishListRequest
import com.microLib.library.domain.model.UserWishList
import com.microLib.library.domain.response.UserWishListResponse
import com.microLib.library.exception.BookAlreadyExistException
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.UserWishListRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserWishListService(
    private val userWishListRepository: UserWishListRepository,
    private val bookListService: BookListService,
    private val userBookService: UserBookService) {

    fun create(request: CreateUserWishListRequest):UserWishListResponse{
        val username=SecurityContextHolder.getContext().authentication.name
        val book=bookListService.findById(request.bookId)
        userBookService.checkBookExistsByBookId(book.id!!,username)
        checkBookExistsByUser(username,request.bookId)
        val userWishList=userWishListRepository.save(UserWishList(
            bookId = book.id,
            username = username))
        return UserWishListResponse.convert(userWishList)
    }

    fun getAllByUsername(username:String):List<UserWishList>{
        return userWishListRepository.findByUsername(username)?:throw UserNotFoundException("User not found with username $username")
    }


    private fun checkBookExistsByUser(username:String,bookId:String){
        if(userWishListRepository.existsByBookIdAndUsername(bookId,username)){
            throw BookAlreadyExistException("Book already exists in user's wish list")
        }
    }
}