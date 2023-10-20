package com.microLib.library.domain.dto

import com.microLib.library.domain.model.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegisterUserRequest(
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val birthDate: String,
    private val passwordEncoder: PasswordEncoder) {
    companion object {
        fun toUser(registerUserRequest: RegisterUserRequest): User {
            return User(
                name = registerUserRequest.name,
                surname = registerUserRequest.surname,
                email = registerUserRequest.email,
                phoneNumber = registerUserRequest.phoneNumber,
                password = registerUserRequest.passwordEncoder.encode(registerUserRequest.password),
                birthDate = registerUserRequest.birthDate
            )
        }
    }
}
