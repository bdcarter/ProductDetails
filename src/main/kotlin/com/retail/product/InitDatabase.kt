package com.retail.product

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.model.*
import com.retail.product.domain.Price
import com.retail.product.repository.PriceRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitDatabase(val priceRepository: PriceRepository, val amazonDynamoDB: AmazonDynamoDB) : CommandLineRunner {
    @Value("\${amazon.dynamodb.endpoint}")
    lateinit var endpoint: String

    override fun run(vararg args: String?) {
        val logger = LoggerFactory.getLogger(InitDatabase::class.java)
        if (endpoint.isNotEmpty()) {
            logger.info("Creating...")
            val createTableRequest = CreateTableRequest()
                .withTableName("Price")
                .withKeySchema(KeySchemaElement("ProductId", KeyType.HASH))
                .withProvisionedThroughput(ProvisionedThroughput(1L, 1L))
                .withAttributeDefinitions(
                    AttributeDefinition("ProductId", ScalarAttributeType.S)
                )

           amazonDynamoDB.createTable(createTableRequest)
        }
        logger.info("Loading...")
        priceRepository.save(Price("54456119", "3.79", "USD"))
        priceRepository.save(Price("13860428", "12.49", "USD"))
        priceRepository.save(Price("13264003", "5.49", "USD"))
        priceRepository.save(Price("12954218", "0.95", "USD"))
        logger.info("...Fin")
    }
}