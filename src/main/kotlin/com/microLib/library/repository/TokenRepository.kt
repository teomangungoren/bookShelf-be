package com.microLib.library.repository

import com.microLib.library.domain.model.Token
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<Token, String>
