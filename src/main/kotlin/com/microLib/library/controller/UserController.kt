package com.microLib.library.controller

import com.microLib.library.domain.request.ChangePasswordRequest
import com.microLib.library.domain.request.RegisterUserRequest
import com.microLib.library.domain.request.SignInRequest
import com.microLib.library.domain.response.TokenResponse
import com.microLib.library.domain.response.UserResponse
import com.microLib.library.extension.General
import com.microLib.library.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
class UserController(private val userService: UserService) : General(){

    @PostMapping("/register")
    fun register(@RequestBody registerUserRequest: RegisterUserRequest):ResponseEntity<UserResponse>{
        return  ResponseEntity(userService.registerUser(registerUserRequest),HttpStatus.CREATED)
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody signInRequest: SignInRequest):ResponseEntity<TokenResponse>{
        return ResponseEntity(userService.authenticate(signInRequest),HttpStatus.OK)
    }

    @GetMapping("/user/{username}")
    fun getUser(@PathVariable username:String):ResponseEntity<UserResponse>{
        return ResponseEntity(userService.getByUsername(username),HttpStatus.OK)
    }

    @PatchMapping("/password")
    fun changePassword(@RequestBody changePasswordRequest: ChangePasswordRequest):ResponseEntity<*>{
        return ResponseEntity(userService.changePassword(changePasswordRequest),HttpStatus.OK)
    }






}