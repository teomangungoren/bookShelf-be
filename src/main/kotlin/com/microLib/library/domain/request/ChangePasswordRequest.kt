package com.microLib.library.domain.request

data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String
) {
}