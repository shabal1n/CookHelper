package com.example.cookhelper.navigation.recipes


class RecipesItemRepository {
    val recipes = mutableListOf<RecipesItem>(
        RecipesItem(
            1,
            "asdfas",
            "asdfasdf ",
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg",
            100,
            "Snacks"
            ),
        RecipesItem(
            2,
            "asdfas",
            "asdfasdf ",
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg",
            300,
            "Dinner"
        ),
        RecipesItem(
            3,
            "asdfas",
            "asdfasdf ",
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg",
            700,
            "Breakfast"
        ),
        RecipesItem(
            4,
            "asdfas",
            "asdfasdf ",
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg",
            120,
            "Evening dinner"
        ),
        RecipesItem(
            5,
            "asdfas",
            "asdfasdf ",
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg",
            400,
            "Snacks"
        )

    )
    fun getChildrensByParent(parent: String): List<RecipesItem> {
        val children = mutableListOf<RecipesItem>()
        recipes.forEach {
            if (it.parent == parent) {
                children.add(it)
            }
        }
        return children
    }
}