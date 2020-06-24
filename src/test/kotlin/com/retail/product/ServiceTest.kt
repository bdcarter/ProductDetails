package com.retail.product

import com.fasterxml.jackson.databind.ObjectMapper
import com.retail.product.domain.Price
import com.retail.product.domain.ProductWithPrice
import com.retail.product.exception.PriceNotFoundException
import com.retail.product.exception.ProductNotFoundException
import com.retail.product.repository.PriceRepository
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.HttpStatus

class ServiceTest : StringSpec() {
    init {
        val id = "54456119"
        val price = Price(id, "983", "USD")
        val productAndPrice =
            ProductWithPrice(id, "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;", price.getPrice(), "USD")
        val helperFunctions = HelperFunctions()
        val productClient = mockk<ProductClient>()
        val repository = mockk<PriceRepository>()
        val objectMapper = mockk<ObjectMapper>()
        val productService = ProductService(productClient, repository, objectMapper)
        every { repository.findAllByProductId(any()) } returns price
        every { productClient.retrieveProductDetails(any()) } returns helperFunctions.getClientRequestJson()

        "ProductService.getProductInfo should return OK when product info and price is found" {
            val result = productService.getProductInfo(id)
            assertSoftly {
                result.statusCode shouldBe HttpStatus.OK
                result.body shouldBe productAndPrice
            }
        }

        "ProductService.getProductInfo should return NOT_FOUND when product info is unavailable" {
            every { productClient.retrieveProductDetails(any()) } throws ProductNotFoundException(id, "Error")
            val result = productService.getProductInfo(id)

            result.statusCode shouldBe HttpStatus.NOT_FOUND
        }

        "ProductService.getProductInfo should return NOT_FOUND when price info is unavailable" {
            every { repository.findAllByProductId(any()) } throws PriceNotFoundException(id)
            val result = productService.getProductInfo(id)
            result.statusCode shouldBe HttpStatus.NOT_FOUND
        }

    }

}
