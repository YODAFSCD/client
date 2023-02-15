package com.example.client.repository

import com.example.client.model.DetailView
import com.example.client.model.ProductView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetailViewRepository:JpaRepository<DetailView, Long> {
    fun findById(id: Long?):DetailView?

}
