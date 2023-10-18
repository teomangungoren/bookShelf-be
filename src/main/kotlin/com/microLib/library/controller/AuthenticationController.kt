package com.microLib.library.controller

import com.microLib.library.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/auth")
class AuthenticationController(private val userService: UserService)
