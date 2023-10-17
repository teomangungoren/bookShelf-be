package com.microLib.library.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter :OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader=request.getHeader("Authorization")
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response)
            return
        }
       val jwt=authHeader.substring(7)
        val userEmail=

    }

}