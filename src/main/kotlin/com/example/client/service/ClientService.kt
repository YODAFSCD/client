package com.example.client.service

import com.example.client.model.Client
import com.example.client.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException



@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list ():List<Client>{
        return clientRepository.findAll()
    }

    fun save (client: Client):Client {
        try {
            client.fullname?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("fullname no debe ser vacio")
            return clientRepository.save(client)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
    fun update(client: Client):Client{
        try {
            clientRepository.findById(client.id)
                ?: throw Exception("El id ${client.id}")
            return clientRepository.save(client)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun updateName(client: Client):Client{
        try{
            val response = clientRepository.findById(client.id)
                ?:throw Exception("El id ${client.id} en cliente no existe")
            response.apply{
                fullname = client.fullname
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }


}