package com.microLib.library.repository

import com.microLib.library.domain.model.Category
import com.microLib.library.domain.response.BookResponse
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, String> {

    fun findCategoryById(id: String): Category?

    fun findCategoryByName(name: String): Category?

}
