package com.microLib.library.repository

import com.microLib.library.domain.model.Book
import com.microLib.library.domain.model.SearchCriteria
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class BookSpecification(private val criteria:SearchCriteria):Specification<Book> {
    override fun toPredicate(root: Root<Book>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
          return when(criteria.operation){
              ":" -> criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.key)), "%${criteria.value.toString().lowercase()}%")
              ">" -> criteriaBuilder.greaterThan(criteriaBuilder.lower(root.get(criteria.key)), criteria.value.toString().lowercase())
              "<" -> criteriaBuilder.lessThan(criteriaBuilder.lower(root.get(criteria.key)), criteria.value.toString().lowercase())
              else -> null
            }
    }
}