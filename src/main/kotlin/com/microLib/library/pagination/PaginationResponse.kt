package com.microLib.library.pagination

data class PaginationResponse<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Long,
    val currentPage: Int
)