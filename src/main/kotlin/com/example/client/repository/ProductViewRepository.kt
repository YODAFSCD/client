package com.example.client.repository

import com.example.client.model.InvoiceView
import com.example.client.model.ProductView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductViewRepository:JpaRepository<ProductView, Long> {

    fun findById(id: Long?):ProductView?

}
