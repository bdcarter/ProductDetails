package com.retail.product.exception

class PriceNotFoundException(id: String) : RuntimeException("Price for product with id $id not found")