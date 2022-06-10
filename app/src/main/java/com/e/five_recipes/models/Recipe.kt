package com.e.five_recipes.models

import com.e.five_recipes.database.entity.DbMyRecipe
import com.e.five_recipes.database.entity.DbRecipe
import com.e.five_recipes.database.entity.RecipeWithIngredientsAndSteps
import com.e.five_recipes.models.response.RecipeResponseNew

data class Recipe(
    val id: String,
    val name: String,
    val summary: String,
    val imagePath: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val isFavourite: Boolean = false
    //val isFavourite,
    //val isMyRecipe
)

fun RecipeResponse.toRecipe() = Recipe(
    id,
    name,
    summary,
    imageURL,
    ingredients.map { it.toIngredient() },
    steps,
    //false,
    //false
)

fun RecipeResponseNew.toRecipe() = Recipe(
    id,
    name,
    summary,
    imagePath,
    ingredients.map{it.toIngredient() },
    steps,
    //false
    //false
)

fun Recipe.toDbRecipe(isFavourite: Boolean) = DbRecipe(
    id,
    name,
    summary,
    imagePath,
    isFavourite
//    ingredients.map { it.toDbIngredient() },
//    steps
)

fun RecipeWithIngredientsAndSteps.toRecipe(isFavourite: Boolean) = Recipe(
    recipe.recipeId,
    recipe.name,
    recipe.summary,
    recipe.imagePath,
    ingredients.map { it.toIngredient() },
    steps.map { it.text },
    isFavourite
)

