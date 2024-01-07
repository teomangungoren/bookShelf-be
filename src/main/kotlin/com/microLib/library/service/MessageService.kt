package com.microLib.library.service

import com.microLib.library.domain.model.Message
import com.microLib.library.domain.request.CreateMessageRequest
import com.microLib.library.repository.MessageRepository
import org.springframework.security.core.context.SecurityContextHolder

class MessageService(private val messageRepository: MessageRepository) {

    fun sendMessage(request:CreateMessageRequest){
        val username=SecurityContextHolder.getContext().authentication.name
        messageRepository.save(Message(
            message = request.message,
            senderUsername = username,
            receiverUsername = request.receiverUsername
        ))
    }
}