package com.example.cookhelper

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.cookhelper.extensions.loadImage
import com.example.cookhelper.navigation.recipes.RecipesItem
import kotlinx.android.synthetic.main.activity_meal.*


class Meal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        val recipe = intent.getSerializableExtra("recipe") as RecipesItem
        meal_name.text = recipe.content
        image_meal.loadImage(recipe.image)
        meal_description.text = recipe.details
        meal_description.movementMethod = ScrollingMovementMethod()
    }

}
