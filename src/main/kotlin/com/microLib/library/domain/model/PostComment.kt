package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant


@Entity
@Table(name = "post_comment")
data class PostComment(
    val postId:String,
    val username:String,
    val comment:String,
    var likes:Int = 0,
    @CreatedDate var createdAt: Instant? = null,
    @LastModifiedDate var lastModifiedAt: Instant? = null
):BaseEntity()