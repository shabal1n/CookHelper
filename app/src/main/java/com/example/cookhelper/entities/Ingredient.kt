package com.example.cookhelper.entities

data class Ingredient(
    var id : Int? = 0,
    var recipe: Recipe,
    var product: Product
) {
}