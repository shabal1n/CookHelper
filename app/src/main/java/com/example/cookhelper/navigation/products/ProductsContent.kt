package com.example.cookhelper.navigation.products

data class ProductsItem(
    val id: String,
    val content: String,
    val details: String,
    val type: ProductsType,
    val quantity: String
) {
    override fun toString(): String = content
}

enum class ProductsType {
    INFO
}