package com.microLib.library.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
    val name: String,
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    val books: List<Book> ?= emptyList()
):BaseEntity()
