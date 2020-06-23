package com.retail.product

import com.amazonaws.Response
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import com.retail.product.domain.Price
import com.retail.product.domain.ProductWithPrice
import com.retail.product.exception.PriceNotFoundException
import com.retail.product.repository.PriceRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.HttpClientErrorException

@Service
class ProductService(
    val productClient: ProductClient,
    val priceRepository: PriceRepository,
    val objectMapper: ObjectMapper
) {
    fun getProductInfo(id: String): ResponseEntity<Any> {
        return try {
            val price = priceRepository.findAllByProductId(id) ?: throw PriceNotFoundException(id)
            ResponseEntity.ok(
                ProductWithPrice(
                    productId = id,
                    title = JsonPath.read(
                        productClient.retrieveProductDetails(id),
                        "$.product.item.product_description.title"
                    ),
                    price = price.getPrice(),
                    currency = price.getCurrency()
                )
            )
        } catch (httpClientError: HttpClientErrorException) {
            ResponseEntity.status(httpClientError.statusCode).body("${httpClientError.message}")
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("${ex.message}")
        }
    }

    fun updateProductInfo(id: String, body: String): ResponseEntity<Any> {
        return try {
            val price = objectMapper.readValue(body, Price::class.java)
            ResponseEntity.ok(priceRepository.save(price))
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("${ex.message}")
        }

    }
}
