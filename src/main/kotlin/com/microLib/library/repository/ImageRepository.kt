package com.microLib.library.repository

import com.microLib.library.domain.model.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository: JpaRepository<Image, String> {

    fun findImageById(id:String):Image

    fun findImageByUsername(username:String):Image
}