package com.example.client.service


import com.example.client.model.Product
import com.example.client.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

    @SpringBootTest
    class ProductServiceTest {

        @InjectMocks
        lateinit var productService: ProductService

        @Mock
        lateinit var productRepository: ProductRepository

        val productMock= Product().apply {
            id=1
            description="ABC-2354"
            brand="g"
            stock= 5
            price= 2.5
        }

        @Test
        fun saveDescriptionCorrect(){
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            val response = productService.save(productMock)
            Assertions.assertEquals(response.id, productMock.id)
        }

            @Test
            fun saveDescriptionWhenIsBlank(){
                Assertions.assertThrows(Exception::class.java) {
                    productMock.apply { description=" "}
                    Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
                    productService.save(productMock)
                }
            }

        @Test
        fun saveProductWhenNoScript() {
            Assertions.assertThrows(Exception::class.java) {
                productMock.apply { description = "ABC 17698" }
                for ((i, v) in productMock.description!!.withIndex()) {
                    println("[$i, $v]")

                    Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
                    productService.save(productMock)
                }
            }
        }

            @Test
            fun saveProductWhenIHaveTwoScripts() {
                Assertions.assertThrows(Exception::class.java) {
                    productMock.apply { description = "ABC--7698" }

                        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
                        productService.save(productMock)

                }

            }
        @Test
        fun saveProductWhenFirstLetter() {
            Assertions.assertThrows(Exception::class.java) {
                productMock.apply { description = "ABC 7698" }

                    Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
                    productService.save(productMock)

            }

        }
        @Test
        fun saveProductWhenHas8Characters() {
            Assertions.assertThrows(Exception::class.java) {
                productMock.apply { description = "ABC-7698" }
                    Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
                    productService.save(productMock)
                }
            }
        @Test
        fun saveProductWhenHavingNo8Characters() {
            Assertions.assertThrows(Exception::class.java) {
                productMock.apply { description = "ABC-76980" }
                Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
                productService.save(productMock)
            }
        }

        }
