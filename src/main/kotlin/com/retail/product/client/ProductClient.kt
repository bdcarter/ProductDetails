package com.retail.product.client

import com.retail.product.exception.ProductNotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
class ProductClient(val restTemplate: RestTemplate) {
    fun retrieveProductDetails(id: String): String? {
        val url =
            "https://redsky.target.com/v2/pdp/tcin/${id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"
        return try {
            restTemplate.getForObject(url, String)
        } catch (ex: RestClientException) {
            throw ProductNotFoundException(id)
        }
    }
}
