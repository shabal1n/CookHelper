package com.example.cookhelper.navigation.recipes

data class RecipesItem(
    val id: Int,
    var content: String,
    val details: String,
    val image: String,
    val quantity: Int,
    val parent: String
) {

    override fun toString(): String = content

}
