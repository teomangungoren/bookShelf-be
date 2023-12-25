package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Entity
@Table(name = "image")
data class Image(
    val name:String,
    val type:String,
    @Lob
    val imageData:ByteArray,
    val username:String
):BaseEntity()