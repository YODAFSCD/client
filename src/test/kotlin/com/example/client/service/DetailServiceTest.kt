package com.example.client.service


import com.example.client.model.Detail
import com.example.client.model.Product
import com.example.client.repository.DetailRepository
import com.example.client.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.Column

@SpringBootTest
    class DetailServiceTest {

        @InjectMocks
        lateinit var detailService: DetailService

        @Mock
        lateinit var detailRepository: DetailRepository

        val detailMock= Detail().apply {
            id=1
            quantity= 1
            invoiceId= 1
            productId= 1

        }

        @Test
        fun saveDetailCorrect(){
            Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(detailMock)
            val response = detailService.save(detailMock)
            Assertions.assertEquals(response.id, detailMock.id)
        }

            @Test
            fun saveProductWhenQuantityIsBlank(){
                Assertions.assertThrows(Exception::class.java) {
                    detailMock.apply { quantity=0}
                    Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(detailMock)
                    detailService.save(detailMock)
                }
            }

        }



