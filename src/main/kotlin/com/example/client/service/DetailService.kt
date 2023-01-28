package com.example.client.service

import com.example.client.model.Detail
import com.example.client.model.Product
import com.example.client.repository.DetailRepository
import com.example.client.repository.InvoiceRepository
import com.example.client.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository

    @Autowired
    lateinit var productRepository:ProductRepository

    @Autowired
    lateinit var invoiceRepository:InvoiceRepository


    fun list ():List<Detail>{
        return detailRepository.findAll()
    }

    fun calculateAndUpdateTotal (detail : Detail){
        val totalCalculated = detailRepository.sumTotal(detail.invoiceId)
        val invoiceResponse = invoiceRepository.findById(detail.invoiceId)
        invoiceResponse.apply {
        total=totalCalculated
        }
        invoiceRepository.save(invoiceResponse)
    }

    fun save(detail: Detail): Detail {
            try{
                detail.quantity?.takeIf { it > 0 }
                    ?: throw Exception("quantity no debe 0")
                val response=detailRepository.save(detail)
                val responseProduct:Product = productRepository.findById(response.productId)
                responseProduct.apply {
                    stock = stock?.minus(detail.quantity!!)
                }
                productRepository.save(responseProduct)
                calculateAndUpdateTotal(response)
                return response
            }
            catch (ex:Exception){
                throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
            }

    }

    fun update(detail: Detail):Detail{
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("El id ${detail.id} en detalle no existe")
            return detailRepository.save(detail)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(detail: Detail):Detail{
        try{
            val response = detailRepository.findById(detail.id)
                ?:throw Exception("El ${detail.id} en detalle no existe")
            response.apply{
                quantity = detail.quantity
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }

}
