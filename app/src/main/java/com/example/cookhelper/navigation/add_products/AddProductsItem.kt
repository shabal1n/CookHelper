package com.example.cookhelper.navigation.add_products


data class AddProductsItem(
    val id: Int? = null,
    var content: String = "",
    val details: String? = null,
    val image: String? = null
) {
    override fun toString(): String = content
}
