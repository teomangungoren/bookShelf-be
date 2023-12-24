package com.microLib.library.extension

import com.microLib.library.domain.model.User
import io.jsonwebtoken.Claims
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class General {
    companion object {
        @JvmStatic
        fun <T> getOrDefaultValue(data: T?, defaultValue: T): T {
            return data ?: defaultValue
        }
    }

    fun Claims.getUserId(): String {
        return this["userId"] as String
    }

    fun String.extractUsername():String{
        return this.substringBefore("@")
    }

    fun LocalDateTime.toDate(): Date {
        return Date.from(this.atZone(java.time.ZoneId.systemDefault()).toInstant())
    }

    @Suppress("UNCHECKED_CAST")
    fun Claims.getRoles(): List<String> {
        return this["roles"]!! as List<String>
    }

    fun <T> withAuthentication(block: (User) -> T?): T? {
        return getAuthentication(block)
    }

    fun <T> getAuthentication(block: (User) -> T?): T? {
        val authentication = SecurityContextHolder.getContext().authentication
        return (authentication.principal as? User)?.let(block)
    }



}
