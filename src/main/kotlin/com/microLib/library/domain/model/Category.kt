package com.microLib.library.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "category")
data class Category(
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    val id: String? = null,
    val name: String,
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    val books: List<Book> ?= emptyList()
)
