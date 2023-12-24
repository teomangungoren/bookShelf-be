package com.microLib.library.controller

import com.microLib.library.service.ImageService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/image")
class ImageController(private val imageService: ImageService) {

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/upload")
    fun uploadImage(@RequestParam("image") image: MultipartFile) {
        imageService.uploadImage(image)
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/get")
    fun getImageByUsername(@RequestParam("username") username: String): ResponseEntity<ByteArray> {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpeg")).body(imageService.getImageByUsername(username))
    }
}