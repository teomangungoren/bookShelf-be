package com.microLib.library.domain.converter

import com.microLib.library.domain.dto.CreateBookRequest
import com.microLib.library.domain.model.Book
import com.microLib.library.domain.model.Category

class BookDtoConverter {

    companion object{
        @JvmStatic
        fun convertToBookDto(createBookRequest: CreateBookRequest,category: Category):Book{
            return with(createBookRequest){
                Book(
                    title=title,
                    author=author,
                    bookYear=bookYear,
                    pressYear=pressYear,
                    isbn=isbn,
                    totalPageNumber=totalPageNumber,
                    category = category
                )
            }
        }
    }
}