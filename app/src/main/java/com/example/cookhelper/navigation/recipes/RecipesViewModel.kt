package com.example.cookhelper.navigation.recipes

import com.example.cookhelper.base.BaseViewModel

class RecipesViewModel : BaseViewModel() {

    private val recipesItemRepository = RecipesItemRepository()
    private val titles =  arrayListOf( "Snacks", "Breakfast", "Dinner", "Evening dinner")


    fun getParents() : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        titles.forEach {
            parents.add(ParentModel(it, recipesItemRepository.getChildrenByParent(it)))
        }
        return parents
    }

}