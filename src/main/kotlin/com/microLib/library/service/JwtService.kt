package com.microLib.library.service

import com.microLib.library.domain.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.time.LocalDateTime
import java.util.Base64
import java.util.Date
import kotlin.math.exp

@Service
class JwtService {

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun generateToken( user: UserDetails): String {
        val claims= user.authorities.joinToString { it.authority }.let {
            mapOf("roles" to it)
        }
        val expirationDate=LocalDateTime.now().plusHours(10)
        return buildToken(claims,user, expirationDate.toDate())
    }

    private fun buildToken(extractClaims: Map<String, Any>,
                   userDetails: UserDetails,expiration:Date):String{
        return Jwts
            .builder()
            .setClaims(extractClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(LocalDateTime.now().toDate())
            .setExpiration(expiration)
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
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun LocalDateTime.toDate():Date{
        return Date.from(this.atZone(java.time.ZoneId.systemDefault()).toInstant())
    }

    companion object {
         const val SECRET_KEY: String = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"
    }
}
