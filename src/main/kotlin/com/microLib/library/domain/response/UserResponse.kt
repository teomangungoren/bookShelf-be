package com.microLib.library.domain.response

import com.microLib.library.domain.model.User
import com.microLib.library.service.ImageService
import java.io.File

data class UserResponse(
    val name: String,
    val surname: String,
    val email: String,
    val profileImage: ByteArray,
    val phoneNumber: String,
    val creationTime: String,
) {

    companion object {
        @JvmStatic
        fun convert(from: User): UserResponse {
            return with(from) {
                UserResponse(
                    name = name,
                    surname = surname,
                    email = email,
                    profileImage = profileImage!!,
                    phoneNumber = phoneNumber,
                    creationTime = creationTime.toString(),
                )
            }
        }
    }
}
