package com.e.five_recipes.models

import com.google.firebase.firestore.PropertyName

data class IngredientResponse(
    @PropertyName("key") val id: String,
    @PropertyName("name") val name: String,
    @PropertyName("quantity") val quantity: Double,
    @PropertyName("unit") val unit: String
) {
    constructor() : this("", "", 0.0, "")
}
