package com.microLib.library.domain.dto

import com.microLib.library.domain.model.User

data class UserResponse(
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val birthDate: String,
    val creationTime: String
) {
    companion object {
        fun convert(from: User): UserResponse {
            return with(from) {
                UserResponse(
                    name = name,
                    surname = surname,
                    email = email,
                    phoneNumber = phoneNumber,
                    birthDate = birthDate,
                    creationTime = creationTime.toString()
                )
            }
        }
    }
}
