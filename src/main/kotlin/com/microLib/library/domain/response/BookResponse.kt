package com.microLib.library.domain.response

import com.microLib.library.domain.model.Book

data class BookResponse(
    val id:String,
    val title: String,
    val author: String,
    val bookYear: Int,
    val pressYear: Int,
    val isbn: String,
    val totalPageNumber: Int,
    var totalRating: Float,
    val imageUrl: String,
    val totalOwner:Int,
    val categoryName: String
) {
    companion object {
        @JvmStatic
        fun convert(from: Book): BookResponse {
            return BookResponse(
                id = from.id!!,
                title = from.title,
                author = from.author,
                bookYear = from.bookYear,
                pressYear = from.pressYear,
                isbn = from.isbn,
                totalPageNumber = from.totalPageNumber,
                totalRating = from.totalRating,
                imageUrl = from.imageUrl,
                totalOwner = from.totalOwner,
                categoryName = from.category.name
            )
        }
    }
}
