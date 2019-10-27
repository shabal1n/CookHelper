package com.example.cookhelper.navigation.recipes

import java.util.*


object ParentContent{
    private val random = Random()

    private val titles =  arrayListOf( "Snacks", "Breakfast", "Daily dinner", "Evening dinner")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomChildren() : List<ChildModel>{
        return ChildContent.getChildren(20)
    }

    fun getParents(count : Int) : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        repeat(count){
            val parent = ParentModel(randomTitle(), randomChildren())
            parents.add(parent)
        }
        return parents
    }
}