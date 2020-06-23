package com.retail.product

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Config {
    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate = restTemplateBuilder.build()

    @Bean
    fun logger(): Logger = LoggerFactory.getLogger(RetailProductDetails::class.java)

    @Bean
    fun objectMapper(): ObjectMapper =
        ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

}
