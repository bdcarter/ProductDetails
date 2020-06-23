# ProductDetails

### How to run:
##### Download the code and run `docker-compose up` to start the local DynamoDB instance
      
##### Run with `gradle`

### Endpoints:
#### GET:
      /product-details/products/{id}
#### PUT:
     /product-details/products/{id}
          body: {
                  "productId":"{id}",
                  "price":"{price}",
                  "currency":"USD"
                }
                
### Response:
           {
             "productId": "54456119",
             "title": "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;",
             "price": "3.79",
             "currency": "USD"
            }
            
