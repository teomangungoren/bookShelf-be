package com.microLib.library.domain.response

import com.microLib.library.domain.model.User

data class UserResponse(
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
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
                    creationTime = creationTime.toString()
                )
            }
        }
    }
}
