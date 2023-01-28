package com.example.client.service

import com.example.client.model.Client
import com.example.client.repository.ClientRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

    @SpringBootTest
    class ClientServiceTest {

        @InjectMocks
        lateinit var clientService: ClientService

        @Mock
        lateinit var clientRepository: ClientRepository

        val clientMock = Client().apply {
            id=1
            nui="03106746977"
            fullname="Alison Otavalo"
            address= "Cuenca"
        }

        @Test
        fun saveClientCorrect(){
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            val response = clientService.save(clientMock)
            Assertions.assertEquals(response.id, clientMock.id)
        }



            @Test
            fun saveClientWhenFullnessIsBlank(){
                Assertions.assertThrows(Exception::class.java) {
                    clientMock.apply { fullname=" "}
                    Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
                    clientService.save(clientMock)
                }
            }
        }


