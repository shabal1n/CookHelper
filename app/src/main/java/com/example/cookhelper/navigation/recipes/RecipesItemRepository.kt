package com.example.cookhelper.navigation.recipes

import com.example.cookhelper.entities.Recipe
import com.google.firebase.database.*


class RecipesItemRepository {
    lateinit var ref: DatabaseReference

    val recipes = arrayListOf<Recipe>()
    fun onViewInitializedProducts(): MutableList<Recipe> {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (productSnapshot in dataSnapshot.children) {
                    val recipeTemp: Recipe? = productSnapshot.getValue(Recipe::class.java)
                    recipes.add(recipeTemp!!)
                }
                System.out.println(recipes)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }

        })
        return recipes
    }

    private fun onViewInitializedRecipes(){
            ref = FirebaseDatabase.getInstance().reference.child("recipes")
            ref.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (n in p0.children){
                        var p: Recipe = n.getValue(Recipe::class.java)!!
                        recipes.add(p)
                    }
                }

            })
    }
        fun getChildrenByParent(parent: String): ArrayList<Recipe> {
            onViewInitializedRecipes()
            var recipesData = recipes
            val children = arrayListOf<Recipe>()
            recipesData?.forEach {
                if (it.parent == parent) {
                    children.add(it)
                }
            }
            return children
        }

    }


