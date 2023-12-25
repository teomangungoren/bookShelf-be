package com.microLib.library.service

import com.microLib.library.domain.request.CreateUserWishListRequest
import com.microLib.library.domain.model.UserWishList
import com.microLib.library.domain.response.UserWishListResponse
import com.microLib.library.domain.view.UserWishListView
import com.microLib.library.exception.BookAlreadyExistException
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.UserWishListRepository
import com.microLib.library.repository.UserWishListViewRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserWishListService(
    private val userWishListRepository: UserWishListRepository,
    private val userWishListViewRepository: UserWishListViewRepository,
    private val bookListService: BookListService,
    private val userBookService: UserBookService,
    private val bookSaveService: BookSaveService) {

    fun create(request: CreateUserWishListRequest):UserWishListResponse{
        val username=SecurityContextHolder.getContext().authentication.name
        val book=bookListService.findById(request.bookId)
        userBookService.checkBookExistsByBookId(book.id!!,username)
        checkBookExistsByUser(username,request.bookId)
        val userWishlist=userWishListRepository.save(UserWishList(username,book.id))
        bookSaveService.increaseTotalWishlistOwner(book.id)
        return UserWishListResponse.convert(userWishlist)
    }

    fun getAllByBookId(bookId:String):List<UserWishListView>{
        return userWishListViewRepository.getAllUsersByBookId(bookId)?:throw BookNotFoundException("Book not found with id $bookId")
    }

    fun getAllByUsername(username:String?):List<UserWishListView>{
        val user=username?:SecurityContextHolder.getContext().authentication.name
        return userWishListViewRepository.getAllBooksByUsername(user)?:throw UserNotFoundException("User not found with username $username")
    }

    fun delete(bookId:String){
        val user=SecurityContextHolder.getContext().authentication.name
        val userWishList=userWishListRepository.findByBookIdAndUsername(bookId,user)?:throw BookNotFoundException("Book not found with id $bookId")
        userWishListRepository.delete(userWishList)
    }

    private fun checkBookExistsByUser(username:String,bookId:String){
        if(userWishListRepository.existsByBookIdAndUsername(bookId,username)){
            throw BookAlreadyExistException("Book already exists in user's wish list")
        }
    }

}