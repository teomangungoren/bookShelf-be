package com.microLib.library.domain.request

import com.microLib.library.domain.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.multipart.MultipartFile

data class RegisterUserRequest(
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    ) {
    companion object {
        fun toUser(registerUserRequest: RegisterUserRequest, passwordEncoder: PasswordEncoder): User {
            return User(
                name = registerUserRequest.name,
                surname = registerUserRequest.surname,
                email = registerUserRequest.email,
                phoneNumber = registerUserRequest.phoneNumber,
                password = passwordEncoder.encode(registerUserRequest.password)
            )
        }
    }
}
