package com.microLib.library.domain.dto

import com.microLib.library.domain.model.User

data class UserRegisterRequest(
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val birthDate: String
) {
    companion object {
        fun toUser(userRegisterRequest: UserRegisterRequest): User {
            return User(
                name = userRegisterRequest.name,
                surname = userRegisterRequest.surname,
                email = userRegisterRequest.email,
                phoneNumber = userRegisterRequest.phoneNumber,
                password = userRegisterRequest.password,
                birthDate = userRegisterRequest.birthDate
            )
        }
    }
}
