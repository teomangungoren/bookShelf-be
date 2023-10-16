package com.microLib.library.domain.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.GenericGenerator

data class Book(
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    val id: String? = null,
    var title:String,
    var author:String,
    var bookYear:Int,
    var pressYear:Int,
    val isbn:String,
    var totalPageNumber:Int,
    var count:Int=1,
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category,
)