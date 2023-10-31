package com.microLib.library.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class BookAlreadyExistException(message: String): RuntimeException(message) {
}