package com.example.cookhelper.navigation.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class RecipesItemRepository {
    val recipesItem = MutableLiveData<List<RecipesItem>>()
    private val reference = FirebaseDatabase.getInstance().reference.child("recipes")

    fun onViewInitializedProducts(){
        val postListener = object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val recipes = ArrayList<RecipesItem>()
                dataSnapshot.children.forEach {
                    recipes.add(it.getValue(RecipesItem::class.java)!!)
                }
                recipesItem.value = recipes
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("tag", "loadPost:onCancelled", databaseError.toException())
            }
        }
        reference.addListenerForSingleValueEvent(postListener)
    }

    fun getChildrenByParent(parent: String): List<RecipesItem> {
        val children = mutableListOf<RecipesItem>()
        val recipes = recipesItem.value
        recipes?.forEach {
            if (it.parent == parent) {
                children.add(it)
            }
        }
        return children
    }
}