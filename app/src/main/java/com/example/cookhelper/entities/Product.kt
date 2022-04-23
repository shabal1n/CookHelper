package com.example.cookhelper.entities

data class Product(
    val id: Int,
    var name: String,
    val details: String,
    val image: String
) {
    override fun toString(): String = name

    enum class ProductsType {
        INFO
    }
}
