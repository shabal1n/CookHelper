package com.example.cookhelper.navigation.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookhelper.DataDAO
import com.example.cookhelper.base.BaseViewModel
import com.example.cookhelper.entities.Recipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecipesViewModel : BaseViewModel() {

    private val _recipes = MutableLiveData<List<ParentModel>>()
    val recipes: LiveData<List<ParentModel>> get() = _recipes
    val data = DataDAO()

    init {
        fetchData()
    }

    private fun fetchData() {

        val list: ArrayList<ParentModel> = ArrayList()
        val recipes: ArrayList<Recipe?> = ArrayList()
        val uniqueParents = mutableSetOf<String>()

        var it = data.recipesList.iterator()

        while (it.hasNext()) {
            val model = it.next()
            model?.let { item -> uniqueParents.add(item.parent) }
            recipes.add(model)
        }

        uniqueParents.forEach { parentName ->
            val parentModel = ParentModel(parentName,
                recipes.filter { recipe: Recipe? -> recipe?.parent == parentName } as List<Recipe>)
            list.add(parentModel)
        }

        _recipes.value = list


    }


}