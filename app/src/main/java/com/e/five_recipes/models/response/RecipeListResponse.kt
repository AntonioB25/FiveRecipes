package com.e.five_recipes.models.response

import com.e.five_recipes.models.RecipeResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeListResponse(
    @SerialName("results")
    val recipes: List<RecipeResponseNew>
)
