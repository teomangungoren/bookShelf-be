package com.microLib.library.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "book")
@DynamicUpdate
data class Book(
    var title: String,
    var author: String,
    var bookYear: Int,
    var pressYear: Int,
    var description: String,
    val isbn: String,
    var totalPageNumber: Int,
    var imageUrl:String,
    var totalOwner: Int = 0,
    var totalWishlistOwner: Int? = 0,
    @JsonIgnore
    var totalRating: Float = 0f,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    var category: Category
):BaseEntity()
