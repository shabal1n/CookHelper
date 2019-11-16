package com.example.cookhelper.navigation.recipes

import com.example.cookhelper.base.BaseViewModel
import java.util.*

class RecipesViewModel : BaseViewModel() {

    private val recipesItemRepository = RecipesItemRepository()
    private val random = Random()
    private val titles =  arrayListOf( "Snacks", "Breakfast", "Dinner", "Evening dinner")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    fun getParents() : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        titles.forEach {
            parents.add(ParentModel(it, recipesItemRepository.getChildrensByParent(it)))
        }
        return parents
    }

}