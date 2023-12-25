package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.Instant

@Entity
@Table(name = "book_comment")
data class BookComment(
    val bookId:String,
    val username:String,
    val comment:String,
    var likes:Int=0,
    @CreatedDate val createdDate:Instant
):BaseEntity()
