package com.microLib.library.domain.request

data class UpdateBookRequest(
    val id: String,
    val title: String?,
    val author: String?,
    val bookYear: Int?,
    val pressYear: Int?,
    val isbn: String,
    val totalPageNumber: Int?,
    val count: Int?,
    val categoryId: String?
)
