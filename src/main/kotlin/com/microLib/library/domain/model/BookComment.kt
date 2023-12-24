package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.Instant

@Entity
@Table(name = "book_comment")
data class BookComment(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id:String?=null,
    val bookId:String,
    val username:String,
    val comment:String,
    @CreatedDate val createdDate:Instant
)
