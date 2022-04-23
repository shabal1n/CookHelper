package com.example.cookhelper.entities

data class User(
    var id: Int,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var image: String? = null,
    var gender: String? = null,
    var weight: Int,
    var height: Int
)