package com.microLib.library.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.io.File

@Entity
@Table(name = "image")
data class Image(
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    val id:String?=null,
    val name:String,
    val type:String,
    @Lob
    val imageData:ByteArray,
    val username:String
) {

}