package com.example.cookhelper.navigation.recipes

import com.example.cookhelper.entities.Recipe

data class ParentModel (
    val title : String = "",
    val children : List<Recipe>
)