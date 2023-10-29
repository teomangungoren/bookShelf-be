package com.microLib.library.service

import com.microLib.library.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service

@Service
class LogoutService(private val tokenRepository: TokenRepository): LogoutHandler {


    override fun logout(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {

        val authHeader = request?.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        val jwt: String = authHeader.substring(7)
        val token = tokenRepository.findByToken(jwt)

        token?.let{
            it.expired = true
            it.revoked = true
            tokenRepository.save(it)
        }
        return  SecurityContextHolder.clearContext()
        }
    }
