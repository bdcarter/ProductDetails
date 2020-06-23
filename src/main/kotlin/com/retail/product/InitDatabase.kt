package com.retail.product

import com.retail.product.domain.Price
import com.retail.product.repository.PriceRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitDatabase(val priceRepository: PriceRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val logger = LoggerFactory.getLogger(InitDatabase::class.java)
        logger.info("Loading...")
        priceRepository.save(Price("54456119", "3.79", "USD"))
        priceRepository.save(Price("13860428", "12.49", "USD"))
        priceRepository.save(Price("13264003", "5.49", "USD"))
        priceRepository.save(Price("12954218", "0.95", "USD"))
        logger.info("...Fin")
    }
}