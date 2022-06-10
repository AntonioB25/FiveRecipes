package com.e.five_recipes.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientResponseNew(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("unit")
    val unit: String
)
