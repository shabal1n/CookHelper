package com.example.cookhelper.entities


data class AddProductsItem(
    var id: Int? = null,
    var content: String = "",
    var details: String? = null,
    var image: String? = null
) {
    override fun toString(): String = content
}
