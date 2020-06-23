package com.retail.product.domain

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "Price")
class Price(private var productId: String = "000", private var price: String = "0.00", private var currency: String = "USD") {

    @DynamoDBHashKey(attributeName = "ProductId")
    fun getProductId(): String = productId
    @DynamoDBAttribute(attributeName = "price")
    fun getPrice(): String = price
    @DynamoDBAttribute(attributeName = "currency")
    fun getCurrency(): String = currency


    fun setProductId(productId: String) { this.productId = productId }

    fun setPrice(price: String) { this.price = price }

    fun setCurrency(currency: String) { this.currency = currency }



}