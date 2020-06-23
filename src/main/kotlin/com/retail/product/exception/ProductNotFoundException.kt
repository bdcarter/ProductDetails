package com.retail.product.exception

import java.lang.RuntimeException

class ProductNotFoundException(id: String) : RuntimeException("Product with id $id not found")