package com.example.client.service


import com.example.client.model.Product
import com.example.client.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list ():List<Product>{
        return productRepository.findAll()
    }
    fun save (product: Product):Product {

        try {
            product.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("descricion no debe ser vacio")
          product.description?.takeIf {validatePlate(it)}
             ?: throw Exception("1Error de placa")
            product.description?.takeIf {sizePlate(it)}
                ?: throw Exception("2Error de placa")
            product.description?.takeIf {dashSeparate(it)}
                ?: throw Exception("3Error de placa")

            return productRepository.save(product)

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    fun update(product: Product):Product{
        try {
            productRepository.findById(product.id)
                ?: throw Exception("El id ${product.id} en producto no existe")
            return productRepository.save(product)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(product: Product):Product{
        try{
            val response = productRepository.findById(product.id)
                ?:throw Exception("El ${product.id} en producto no existe")
            response.apply{
                stock = product.stock
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }



    fun validatePlate(plate: String):Boolean{

        return true
    }
    fun sizePlate(plate: String):Boolean{
        val tama = 8
        val  pla = plate.length
        return pla == tama
    }
    fun dashSeparate(plate: String):Boolean{
        val guion = "-"
        return plate.substring(3,4) == guion
    }



}

