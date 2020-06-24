package com.retail.product

class HelperFunctions {

    fun getClientRequestJson(): String =
        """
            {
              "product": {
                "available_to_promise_network": {
                  "product_id": "54456119",
                  "id_type": "TCIN",
                  "available_to_promise_quantity": 22359.0,
                  "availability": "AVAILABLE",
                  "online_available_to_promise_quantity": 0.0,
                  "stores_available_to_promise_quantity": 22359.0,
                  "availability_status": "IN_STOCK",
                  "multichannel_options": [
                    "HOLD",
                    "SHIPGUEST",
                    "BASICS",
                    "SCHEDULED_DELIVERY"
                  ],
                  "is_infinite_inventory": false,
                  "loyalty_availability_status": "IN_STOCK",
                  "loyalty_purchase_start_date_time": "1970-01-01T00:00:00.000Z",
                  "is_loyalty_purchase_enabled": false,
                  "is_out_of_stock_in_all_store_locations": false,
                  "is_out_of_stock_in_all_online_locations": true
                },
                "item": {
                  "tcin": "54456119",
                  "bundle_components": {},
                  "dpci": "212-02-0754",
                  "upc": "085239044926",
                  "product_description": {
                    "title": "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;",
                    "downstream_description": "For a go-to condiment in your kitchen, make sure you have Creamy Peanut Butter from Good & Gather™ on hand. This classic-style peanut butter is smooth and creamy for easy spreading and mixing — perfect for whipping up a batch of your famous peanut butter cookies or blending into a post-workout smoothie. Create a classic PB&J sandwich by pairing with your favorite jelly and type of bread, or mix with chocolate spread and stir into your oatmeal for a warm treat. However you choose to enjoy it, you'll love the peanuty taste and creamy texture of this classic smooth peanut butter, as well as the 7 grams of protein this peanut butter provides in every serving.<br /><br />Every product that carries the Good & Gather™ name starts with quality ingredients that deliver great taste, making it easier for you and your family to eat well, every day. We promise you’ll love each bite, or your money back.",
                  }
                }

              }
            }
        """.trimIndent()
}