package com.example.cookhelper.navigation.recipes


class RecipesItemRepository {
    val recipes = mutableListOf<RecipesItem>(
        RecipesItem(
            1,
            "Pizza",
            "Pizza (Italian: [ˈpittsa], Neapolitan: [ˈpittsə]) is a savory dish of Italian origin, consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients (anchovies, olives, meat, etc.) baked at a high temperature, traditionally in a wood-fired oven.[1] A small pizza is sometimes called a pizzetta.\n" +
                    "\n" +
                    "In Italy, pizza served in formal settings, such as at a restaurant, is presented unsliced and eaten with the use of a knife and fork.[2][3] In casual settings it is cut into wedges to be eaten while held in the hand.\n" +
                    "\n" +
                    "The term pizza was first recorded in the 10th century in a Latin manuscript from the Southern Italian town of Gaeta in Lazio, on the border with Campania.[4] Modern pizza was invented in Naples, and the dish and its variants have since become popular in many countries.[5] It has become one of the most popular foods in the world and a common fast food item in Europe and North America, available at pizzerias (restaurants specializing in pizza), restaurants offering Mediterranean cuisine, and via pizza delivery.[5][6] Many companies sell ready-baked frozen pizzas to be reheated in an ordinary home oven.",
            "https://www.cicis.com/wp-content/uploads/2019/04/pizza_categoryheader.png",
            1,
            "Dinner"
        ),
        RecipesItem(
            2,
            "Burger",
            "American food ",
            "https://www.seriouseats.com/recipes/images/2015/07/20150702-sous-vide-hamburger-anova-primary.jpg",
            3,
            "Dinner"
        ),
        RecipesItem(
            3,
            "Pasta",
            "Italian food ",
            "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/delish-190903-pasta-pomodoro-0201-landscape-pf-1568667704.jpg?crop=0.668xw:1.00xh;0.158xw,0&resize=980:*",
            100,
            "Dinner"
        ),
        RecipesItem(
            4,
            "Soup",
            "Different nationality food ",
            "https://heatherchristo.com/wp-content/uploads/2019/03/47304715951_11223531ed_o.jpg",
            120,
            "Dinner"
        ),
        RecipesItem(
            5,
            "Omelette",
            "Morning ",
            "https://hips.hearstapps.com/del.h-cdn.co/assets/17/19/1494614947-delish-philly-cheesesteak-omelette-1.jpg?crop=0.563xw:1.00xh;0.214xw,0&resize=768:*",
            100,
            "Dinner"
        ),
        RecipesItem(
            6,
            "Croissant",
            "Morning ",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/2018_01_Croissant_IMG_0685.JPG/500px-2018_01_Croissant_IMG_0685.JPG",
            100,
            "Breakfast"
        )

    )

    fun getChildrenByParent(parent: String): List<RecipesItem> {
        val children = mutableListOf<RecipesItem>()
        recipes.forEach {
            if (it.parent == parent) {
                children.add(it)
            }
        }
        return children
    }
}