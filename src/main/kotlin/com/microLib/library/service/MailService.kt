package com.microLib.library.service

import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service

@Service
class MailService(private val mailSender: MailSender) {

    fun sendMail(mail:String){
        val simpleMailMessage=SimpleMailMessage()
        simpleMailMessage.from="apolar1453@gmail.com"
        simpleMailMessage.setTo(mail)
        simpleMailMessage.subject="BookShelf"
        simpleMailMessage.text="Your account has been created successfully"
        mailSender.send(simpleMailMessage)
    }
}