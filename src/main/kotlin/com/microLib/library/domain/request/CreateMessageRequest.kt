package com.microLib.library.domain.request

data class CreateMessageRequest(
    val message:String,
    val receiverUsername:String,
)
