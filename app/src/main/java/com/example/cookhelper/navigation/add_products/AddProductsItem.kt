package com.example.cookhelper.navigation.add_products


data class AddProductsItem(
    val id: Int,
    var content: String,
    val details: String,
    val image: String,
    val quantity: Int
) {
    override fun toString(): String = content
}
