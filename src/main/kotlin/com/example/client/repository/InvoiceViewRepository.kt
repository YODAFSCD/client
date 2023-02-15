package com.example.client.repository

import com.example.client.model.Invoice
import com.example.client.model.InvoiceView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface InvoiceViewRepository:JpaRepository<InvoiceView, Long> {
    fun findById(id: Long?): InvoiceView



}
