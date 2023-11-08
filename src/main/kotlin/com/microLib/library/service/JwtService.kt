package com.microLib.library.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Base64
import java.util.Date

@Service
class JwtService {

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun generateToken(extractClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts
            .builder()
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(emptyMap(), userDetails)
    }

    fun isValidToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

     fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }
    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun getSignInKey(): Key {
        val keyBytes = Base64.getDecoder().decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    companion object {
        private const val SECRET_KEY: String = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"
    }
}
