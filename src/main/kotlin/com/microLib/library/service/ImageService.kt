package com.microLib.library.service

import com.microLib.library.domain.model.Image
import com.microLib.library.repository.ImageRepository
import com.microLib.library.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService(private val imageRepository: ImageRepository,
                   private val userRepository: UserRepository) {

    fun uploadImage(file:MultipartFile){
       userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)?.let {
           val image=imageRepository.save(Image(file.originalFilename!!,file.contentType!!,file.bytes,it.email))
           it.profileImage=image.imageData
              userRepository.save(it)
       }

    }

    @Transactional
    fun getImageByUsername(username:String):ByteArray{
        return imageRepository.findImageByUsername(username).imageData
    }

}