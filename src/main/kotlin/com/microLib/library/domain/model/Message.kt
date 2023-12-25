package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "message")
data class Message(
    val message:String,
    val senderUsername:String,
    val receiverUsername:String,
    ):BaseEntity()