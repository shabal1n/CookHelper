package com.example.cookhelper.navigation.recipes

import java.util.*

object ParentDataFactory{
    private val random = Random()

    private val titles =  arrayListOf( "Snacks", "Breakfast", "Dinner", "Evening dinner")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomChildren() : List<ChildModel>{
        return ChildDataFactory.getChildren(20)
    }

    fun getParents() : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        titles.forEach {
            parents.add(ParentModel(it, randomChildren()))
        }
        return parents
    }
}