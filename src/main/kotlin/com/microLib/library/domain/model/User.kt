package com.microLib.library.domain.model

import com.microLib.library.domain.enum.Role
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
data class User(
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    val id:String?=null,
    val name:String,
    val surname:String,
    var email:String,
    var phoneNumber:String,
    private var password:String,
    @Enumerated(EnumType.STRING)
    var role:Role=Role.USER,
    val birthDate:String,
    @CreationTimestamp
    val creationTime:LocalDateTime= LocalDateTime.now(),
):UserDetails{
    override fun getAuthorities(): MutableCollection<out SimpleGrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun isEnabled(): Boolean {
        return true;
    }
}