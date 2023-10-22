package com.microLib.library.repository

import com.microLib.library.domain.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TokenRepository : JpaRepository<Token, String>{

    @Query("""
        select t
            from Token t
        where t.user.id = :id and (t.expired = false or t.revoked = false)
    """)
    fun findAllValidTokensByUser(id:String):List<Token>;

    fun findByToken(token:String):Token?
}
