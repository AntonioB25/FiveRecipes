package com.e.five_recipes.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseNew(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("summary")
    val summary: String,
    @SerialName("imagePath")
    val imagePath: String,
    @SerialName("ingredients")
    val ingredients: List<IngredientResponseNew>,
    @SerialName("Steps")
    val steps: List<String>
)

