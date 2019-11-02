package com.example.cookhelper.entities

data class Sample(
    var title: String? = null,
    var description: String? = null
) {
    constructor() : this(null, null)
}