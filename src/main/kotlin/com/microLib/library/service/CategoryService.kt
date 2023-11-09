package com.microLib.library.service

import com.microLib.library.domain.model.Category
import com.microLib.library.domain.request.CreateCategoryRequest
import com.microLib.library.domain.response.BookResponse
import com.microLib.library.repository.CategoryRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findById(id: String): Category? {
        return categoryRepository.findCategoryById(id)
    }

    fun findByName(name: String): Category? {
        return categoryRepository.findCategoryByName(name)
    }

    fun create(createCategoryRequest: CreateCategoryRequest): Category {
        return categoryRepository.save(Category(name = createCategoryRequest.name))
    }

    fun getAll():List<Category>{
        return categoryRepository.findAll()
    }
}
