package com.microLib.library.service

import com.microLib.library.domain.view.UserBookView
import com.microLib.library.domain.model.UserBook
import com.microLib.library.domain.request.UserBookRequest
import com.microLib.library.domain.response.UserBookResponse
import com.microLib.library.exception.BookAlreadyExistException
import com.microLib.library.exception.BookNotFoundException
import com.microLib.library.exception.UserNotFoundException
import com.microLib.library.repository.UserBookRepository
import com.microLib.library.repository.UserBookViewRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserBookService(private val userBookRepository: UserBookRepository,
                      private val bookListService: BookListService,
                      private val bookSaveService: BookSaveService,
                      private val userBookViewRepository: UserBookViewRepository
                      ) {

    fun create(request:UserBookRequest):UserBookResponse{
        val username=SecurityContextHolder.getContext().authentication.name
        val book=bookListService.findById(request.bookId)
        checkBookExistsByBookId(book.id!!,username)
        val userBook=userBookRepository.save(UserBook("",username,book.id,request.rating))
        bookSaveService.calculateRating(book.id,request.rating)
        return UserBookResponse.convert(userBook)
    }

    fun getAllByUsername(username:String):List<UserBook>{
        return userBookRepository.findByUsername(username)?:throw UserNotFoundException("User not found with username $username")
    }

    fun getAllByBookId(bookId:String):List<UserBook>{
        return userBookRepository.findByBookId(bookId)?:throw BookNotFoundException("Book not found with id $bookId")
    }

    fun getAllUsersByBookId(bookId:String):List<UserBookView>{
       return userBookViewRepository.getAllUsersByBookId(bookId)?:throw BookNotFoundException("Book not found with id $bookId")
    }

    fun delete(bookId:String){
        val username=SecurityContextHolder.getContext().authentication.name
        val userBook=userBookRepository.findByBookIdAndUsername(bookId,username)?:throw BookNotFoundException("Book not found with id $bookId")
        userBookRepository.delete(userBook)
    }


    fun checkBookExistsByBookId(bookId:String,username:String){
        require(!userBookRepository.existsByBookIdAndUsername(bookId,username)){
            throw BookAlreadyExistException("Book already exists in user's book list")
        }
    }




}