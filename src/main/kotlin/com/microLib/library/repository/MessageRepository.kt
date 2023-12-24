package com.microLib.library.repository

import com.microLib.library.domain.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, String> {
}