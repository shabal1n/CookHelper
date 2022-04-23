package com.example.cookhelper.entities

data class Step_ingredients(
    var ingredient_id: Int? = 0,
    var recipe_id: Int? = 0,
    var step_id: Int? = 0,
    var amount: Int? = 0,
    var unit: String? = null
) {
}