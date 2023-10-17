package com.microLib.library.repository

import com.microLib.library.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,String> {

    fun findByEmail(email:String):User?
}