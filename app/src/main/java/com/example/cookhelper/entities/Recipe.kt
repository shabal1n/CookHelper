package com.example.cookhelper.entities

import java.io.Serializable

data class Recipe(
    val id: Int = 0,
    val category: Recipe_category,
    var name: String = "",
    val description: String = "",
    val image: String = "",
    val parent: String
): Serializable {

    override fun toString(): String = name


}
