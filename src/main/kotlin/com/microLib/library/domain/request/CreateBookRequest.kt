package com.microLib.library.domain.request

data class CreateBookRequest(
    val title: String,
    val author: String,
    val bookYear: Int,
    val pressYear: Int,
    val description: String,
    val isbn: String,
    val totalPageNumber: Int,
    val imageUrl: String,
    val categoryId: String
)
