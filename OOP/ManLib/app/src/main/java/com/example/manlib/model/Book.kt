package com.example.manlib.model

data class Book(
    val id: Int,
    val title: String,
    var isSelected: Boolean = false
)