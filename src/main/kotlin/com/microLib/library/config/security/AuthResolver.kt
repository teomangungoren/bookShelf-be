package com.microLib.library.config.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthResolver {

    fun getUsernamePasswordAuthenticationToken(): UsernamePasswordAuthenticationToken {
        val authentication = SecurityContextHolder.getContext().authentication
        require (authentication is UsernamePasswordAuthenticationToken) {
            throw IllegalStateException("Authentication is not a UsernamePasswordAuthenticationToken")
        }
        return authentication
    }

    fun getUserId(): String {
        return getUsernamePasswordAuthenticationToken().getUserId()
    }

    fun <T> withAuth(body: (UsernamePasswordAuthenticationToken) -> T): T {
        return body(getUsernamePasswordAuthenticationToken())
    }

    fun UsernamePasswordAuthenticationToken.getUserId(): String {
        val principal = this.principal as? Pair<*, *> ?: throw IllegalStateException("Principal is not a Pair")
        return principal.second.toString()
    }
}
