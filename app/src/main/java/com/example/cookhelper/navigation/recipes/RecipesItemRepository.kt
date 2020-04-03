package com.example.cookhelper.navigation.recipes

import com.google.firebase.database.*


class RecipesItemRepository {
    lateinit var ref: DatabaseReference

    val recipes = arrayListOf<RecipesItem>()
//    fun onViewInitializedProducts(): MutableList<RecipesItem> {
//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (productSnapshot in dataSnapshot.children) {
//                    val recipeTemp: RecipesItem? = productSnapshot.getValue(RecipesItem::class.java)
//                    recipes.add(recipeTemp!!)
//                }
//                System.out.println(recipes)
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                throw databaseError.toException()
//            }
//
//        })
//        return recipes
//    }
    fun onViewInitializedRecipes(){
            ref = FirebaseDatabase.getInstance().reference.child("recipes")
            ref.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (n in p0.children){
                        var p: RecipesItem = n.getValue(RecipesItem::class.java)!!
                        recipes.add(p)
                    }
                }

            })
    }
        fun getChildrenByParent(parent: String): ArrayList<RecipesItem> {
            onViewInitializedRecipes()
            var recipesData = recipes
            val children = arrayListOf<RecipesItem>()
            recipesData?.forEach {
                if (it.parent == parent) {
                    children.add(it)
                }
            }
            return children
        }

    }


