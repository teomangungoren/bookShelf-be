package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "message")
data class Message(
    val message:String,
    val senderUsername:String,
    val receiverUsername:String,
    val createdDate:LocalDateTime ? = LocalDateTime.now()
    ):BaseEntity()