package com.e.five_recipes.models

data class Recipe(
    val id: String,
    val name: String,
    val summary: String,
    val imagePath: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)

