package com.microLib.library.domain.model

import com.microLib.library.domain.enum.TokenType
import jakarta.persistence.*

@Entity
@Table(name = "tokens")
data class Token(
    val token: String,
    val tokenType: TokenType? = TokenType.BEARER,
    var expired: Boolean,
    var revoked: Boolean,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
):BaseEntity()
