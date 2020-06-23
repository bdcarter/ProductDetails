package com.retail.product.repository


import com.retail.product.domain.Price
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@EnableScan
interface PriceRepository: CrudRepository<Price, String> {
    fun findAllByProductId(id: String): Price?
}

