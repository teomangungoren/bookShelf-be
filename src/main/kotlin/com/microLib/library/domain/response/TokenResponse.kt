package com.microLib.library.domain.response

import java.util.Date

data class TokenResponse(
    val token: String,
    val expirationDate: Date
) {
}