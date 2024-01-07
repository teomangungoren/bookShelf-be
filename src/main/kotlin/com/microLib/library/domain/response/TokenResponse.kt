package com.microLib.library.domain.response

import java.time.LocalDateTime
import java.util.Date

data class TokenResponse(
    val token: String,
    val username:String,
    val expirationDate: Date
) {
}