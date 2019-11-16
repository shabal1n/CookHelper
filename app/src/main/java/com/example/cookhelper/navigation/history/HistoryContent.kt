package com.example.cookhelper.navigation.history

data class HistoryItem(
    val id: Int,
    val content: String,
    val details: String,
    val type: HistoryType,
    val image: String
) {
    override fun toString(): String = content
}

enum class HistoryType {
    DATE,
    INFO
}