package com.e.five_recipes.models

import com.e.five_recipes.database.entity.DbIngredient
import com.e.five_recipes.models.response.IngredientResponseNew

data class Ingredient(
    val id: String,
    val name: String,
    val quantity: Double,
    val unit: String
)

fun IngredientResponse.toIngredient() = Ingredient(
    id,
    name,
    quantity,
    unit
)


fun IngredientResponseNew.toIngredient() = Ingredient(
    id,
    name,
    quantity,
    unit
)

fun Ingredient.toDbIngredient() = DbIngredient(
    id,
    name,
    quantity,
    unit
)

fun DbIngredient.toIngredient() = Ingredient(
    ingredientId,
    name,
    quantity,
    unit
)

