package com.microLib.library.repository

import com.microLib.library.domain.model.Book
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BookRepository : JpaRepository<Book, String>,JpaSpecificationExecutor<Book> {

    fun findBookByIsbn(isbn: String): Book?

    fun findBookById(id: String): Book?

    fun findAllBooksByCategoryId(id:String) : List<Book>
/*
    fun findAllByAuthorContainingAndDescriptionAndTitle(search:String?):List<Book> {
        val  cb= entityManager.criteriaBuilder
        val cq=cb.createQuery(Book::class.java)

        val book=cq.from(Book::class.java)
        val predicates= mutableListOf<Predicate>()

        search?.let {
            predicates.add(cb.like(book.get("author"),"%$it%"))
        }
        search?.let {
            predicates.add(cb.like(book.get("title"),"%$it%"))
        }
        search?.let {
            predicates.add(cb.like(book.get("description"),"%$it%"))
        }

        cq.where(*predicates.toTypedArray())

        return entityManager.createQuery(cq).resultList
*/





}
