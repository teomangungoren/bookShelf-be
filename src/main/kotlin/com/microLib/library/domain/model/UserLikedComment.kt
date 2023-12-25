package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity
@Table(name="user_liked_comment")
data class UserLikedComment(
    val postCommentId:String,
    val username:String,
    @CreatedDate var createdAt: Instant? = null,
    @LastModifiedDate var lastModifiedAt: Instant? = null
):BaseEntity()