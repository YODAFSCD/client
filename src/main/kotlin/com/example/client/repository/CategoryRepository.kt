package com.example.client.repository

import com.example.client.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CategoryRepository:JpaRepository<Category, Long> {
    fun findById(id: Long?): Category
}
