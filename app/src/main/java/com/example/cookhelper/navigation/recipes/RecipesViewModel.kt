package com.example.cookhelper.navigation.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookhelper.base.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecipesViewModel : BaseViewModel() {

    private val _recipes = MutableLiveData<List<ParentModel>>()
    val recipes: LiveData<List<ParentModel>> get() = _recipes

    init {
        fetchData()
    }

    private fun fetchData(){
        FirebaseDatabase.getInstance().reference.child("recipes").addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val list:ArrayList<ParentModel> = ArrayList()
                    val recipes:ArrayList<RecipesItem?> = ArrayList()
                    val uniqueParents = mutableSetOf<String>()
                    p0.children.forEach {
                        val model = it.getValue(RecipesItem::class.java)
                        model?.let { item-> uniqueParents.add(item.parent) }
                        recipes.add(model)
                    }
                    uniqueParents.forEach {parentName->
                        val parentModel = ParentModel(parentName,
                            recipes.filter { recipesItem: RecipesItem? -> recipesItem?.parent == parentName } as List<RecipesItem>)
                        list.add(parentModel)
                    }
                    _recipes.value = list
                }

            }
        )

    }


}