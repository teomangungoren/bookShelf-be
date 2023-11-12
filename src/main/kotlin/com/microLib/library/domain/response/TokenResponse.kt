package com.microLib.library.domain.response

import java.time.LocalDateTime
import java.util.Date

data class TokenResponse(
    val token: String,
    val nowTime:Date,
    val expirationDate: Date
) {
}