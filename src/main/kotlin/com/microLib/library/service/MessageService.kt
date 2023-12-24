package com.microLib.library.service

import com.microLib.library.domain.request.CreateMessageRequest
import com.microLib.library.repository.MessageRepository

class MessageService(private val messageRepository: MessageRepository) {

    fun sendMessage(request:CreateMessageRequest){

    }
}