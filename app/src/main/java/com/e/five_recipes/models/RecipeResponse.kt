package com.e.five_recipes.models

import com.google.firebase.firestore.PropertyName

data class RecipeResponse(
    @PropertyName("key") val id: String,
    @PropertyName("name") val name: String,
    @PropertyName("summary") val summary: String,
    @PropertyName("imageURL") val imageURL: String,
    @PropertyName("ingredients") val ingredients: List<IngredientResponse>,
    @PropertyName("steps") val steps: List<String>

) {
    constructor() : this("", "", "", "", emptyList<IngredientResponse>(), emptyList<String>())
}
