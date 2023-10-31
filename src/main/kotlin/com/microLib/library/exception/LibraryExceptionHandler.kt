package com.microLib.library.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class LibraryExceptionHandler {

    @ExceptionHandler(BookNotFoundException::class)
    fun handleBookNotFoundException(exception: BookNotFoundException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserAlreadyExistException::class)
    fun handleUserAlreadyExistException(exception: UserAlreadyExistException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(BookAlreadyExistException::class)
    fun handleBookAlreadyExistException(exception: BookAlreadyExistException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.CONFLICT)
    }

}
