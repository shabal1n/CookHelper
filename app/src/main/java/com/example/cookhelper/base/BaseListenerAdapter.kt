package com.example.cookhelper.base

interface BaseListenerAdapter<T> {
    fun onClick(pos: Int, item: T)
}