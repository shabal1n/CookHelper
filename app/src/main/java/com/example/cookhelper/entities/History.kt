package com.example.cookhelper.entities

data class History(
    val id: Int,
    val user: User,
    val recipe: Recipe,
    val type: HistoryType,
    val date: String
) {

//    constructor(historyType: HistoryType, date: String) :
//            this(0, User(1, "Artur", "artur.shab@yandex.ru", "artur233", "image",
//                "male", 70, 193),
//                Recipe(1, Recipe_category(1, "Fast food"),"Burger", "Junk food", "image", "Fast food"),
//                historyType, date){
//
//    }

     fun <T : Comparable<T>> dateComparator() =  Comparator<History> { a, b ->
        when {
            (a == null && b == null) -> 0
            (a == null) -> -1
            (b == null) -> 1
            else -> b.date.compareTo(a.date)
        }
    }

    override fun toString(): String = recipe.name

    enum class HistoryType {
        DATE,
        INFO
    }
}