package com.microLib.library.domain.dto

data class UpdateBookRequest(
    val id:String,
    val title:String?,
    val author:String?,
    val bookYear:String?,
    val pressYear:Int?,
    val isbn:String,
    val totalPageNumber:Int?,
    val count:Int?,
    val categoryId:String?,
) {
}