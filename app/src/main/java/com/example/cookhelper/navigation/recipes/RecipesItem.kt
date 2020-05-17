package com.example.cookhelper.navigation.recipes

import java.io.Serializable

data class RecipesItem(
    val id: Int = 0,
    var content: String = "",
    val details: String = "",
    val image: String = "",
    val parent: String = ""
): Serializable {

    override fun toString(): String = content

}
