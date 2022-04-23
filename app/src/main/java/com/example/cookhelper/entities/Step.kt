package com.example.cookhelper.entities

data class Step(
    var id: Int? = 0,
    var recipe_id: Int? = 0,
    var step_number: Int? = 0,
    var description: String? = null,
    var timer: Int? = 0
) {
}