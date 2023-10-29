package com.microLib.library.domain.model

import com.microLib.library.domain.enum.TokenType
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "tokens")
data class Token(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = null,
    val token: String,
    val tokenType: TokenType? = TokenType.BEARER,
    var expired: Boolean,
    var revoked: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)
