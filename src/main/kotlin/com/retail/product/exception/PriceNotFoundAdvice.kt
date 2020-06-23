package com.retail.product.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class PriceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PriceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun priceNotFoundAdvice(exception: PriceNotFoundException) = exception.message
}