package com.microLib.library.domain.dto

data class CreateBookRequest(
    val title: String,
    val author: String,
    val bookYear: Int,
    val pressYear: Int,
    val isbn: String,
    val totalPageNumber: Int,
    val categoryId: String
)
