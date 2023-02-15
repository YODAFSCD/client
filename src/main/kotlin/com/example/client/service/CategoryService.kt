package com.example.client.service

import com.example.client.model.Category
import com.example.client.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service

class CategoryService {
    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun list ():List<Category>{
        return categoryRepository.findAll()
    }
    fun save (category: Category): Category {

        try {

            return categoryRepository.save(category)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    fun update(category: Category): Category {
        try {
            categoryRepository.findById(category.id)
                ?: throw Exception("El id ${category.id} en categoryo no existe")
            return categoryRepository.save(category)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }


    fun delete (id: Long?):Boolean?{
        categoryRepository.findById(id) ?:
        throw  Exception()
        categoryRepository.deleteById(id!!)
        return true
    }

}