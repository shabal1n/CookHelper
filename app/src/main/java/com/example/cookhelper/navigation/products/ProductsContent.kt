package com.example.cookhelper.navigation.products

data class ProductsItem(
    val id: String,
    var content: String,
    val details: String,
    val type: ProductsType,
    val quantity: String
) {
    override fun toString(): String = content


    fun getProductName(): String {
        return content
    }

    fun setProductsName(content: String) {
        this.content = content
    }
}
class ProductsContent{

}
enum class ProductsType {
    INFO
}
