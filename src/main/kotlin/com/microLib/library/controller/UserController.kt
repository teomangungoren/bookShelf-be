package com.microLib.library.controller

import com.microLib.library.domain.dto.AuthenticationResponse
import com.microLib.library.domain.dto.RegisterUserRequest
import com.microLib.library.domain.dto.SignInRequest
import com.microLib.library.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody registerUserRequest: RegisterUserRequest):ResponseEntity<AuthenticationResponse>{
        return ResponseEntity(userService.registerUser(registerUserRequest),HttpStatus.CREATED)
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody signInRequest: SignInRequest):ResponseEntity<AuthenticationResponse>{
        return ResponseEntity(userService.authenticate(signInRequest),HttpStatus.OK)
    }

}