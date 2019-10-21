package com.example.cookhelper.navigation.history

data class HistoryItem(val id: String, val content: String, val details: String, val type: HistoryType ) {
    override fun toString(): String = content
}

enum class HistoryType{
    DATE,
    INFO
}