package com.example.ecommerce5

data class Product(
    val img: String,
    val name: String,
    val price: Double
) {
    constructor(img: String, name: String) :this("Image","Name", 0.00)
}
