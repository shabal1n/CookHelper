package com.example.cookhelper.navigation.products

data class ProductsItem(
    val id: Int,
    var content: String,
    val details: String,
    val image: String
) {
    override fun toString(): String = content

    enum class ProductsType {
        INFO
    }
}
