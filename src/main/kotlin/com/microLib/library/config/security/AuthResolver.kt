package com.microLib.library.config.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthResolver {

    fun UsernamePasswordAuthenticationToken.getUsername(): String {
        val principalPair = this.principal as Pair<String, String>
        return principalPair.first
    }

    fun UsernamePasswordAuthenticationToken.getUserId(): String {
        val principalPair = this.principal as Pair<String, String>
        return principalPair.second
    }

    fun getAuth(): UsernamePasswordAuthenticationToken {
        return SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
    }

    fun <T> withAuth(body: (UsernamePasswordAuthenticationToken) -> T): T = body(getAuth())
}
