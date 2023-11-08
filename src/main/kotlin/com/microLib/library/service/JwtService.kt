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
import kotlin.math.exp

@Service
class JwtService {

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun generateToken(extractClaims: Map<String, Any>, userDetails: UserDetails): String {
        return buildToken(extractClaims, userDetails, JWT_EXPIRATION)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(emptyMap(), userDetails)
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        return buildToken(emptyMap(), userDetails, REFRESH_EXPIRATION)
    }

    private fun buildToken(extractClaims: Map<String, Any>,
                   userDetails: UserDetails,
                   expiration:Long):String{
        return Jwts
            .builder()
            .setClaims(extractClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
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
         const val SECRET_KEY: String = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"
         const val JWT_EXPIRATION=86400000L
         const val REFRESH_EXPIRATION=604800000L


    }
}
