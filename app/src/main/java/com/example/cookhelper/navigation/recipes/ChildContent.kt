package com.example.cookhelper.navigation.recipes

import com.example.cookhelper.R
import java.util.*

object ChildContent{

    private val random = Random()

    private val titles =  arrayListOf( "Lagman", "Teriyaki shicken", "Pasta", "Pizza", "Burger")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomImage() : Int{
        return R.drawable.ic_kitchen_black_24dp
    }

    fun getChildren(count : Int) : List<ChildModel>{
        val children = mutableListOf<ChildModel>()
        repeat(count){
            val child = ChildModel(randomImage(), randomTitle())
            children.add(child)
        }
        return children
    }


}