package com.microLib.library.domain.response

import com.microLib.library.domain.model.Category

data class CategoryResponse(
    val id: String,
    val name: String
){
    companion object {
        @JvmStatic
        fun convert(from:Category): CategoryResponse {
            return CategoryResponse(
                id = from.id!!,
                name = from.name
            )
        }
    }
}
