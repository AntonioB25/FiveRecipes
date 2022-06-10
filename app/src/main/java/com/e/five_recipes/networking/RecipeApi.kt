package com.e.five_recipes.networking

import com.e.five_recipes.models.RecipeResponse
import com.e.five_recipes.models.response.RecipeListResponse
import io.ktor.client.*

interface RecipeApi {
    suspend fun fetchRecipes(): RecipeListResponse
    suspend fun fetchDetails(recipeId: String): RecipeResponse
}

internal class RecipeApiImpl(
    private val client: HttpClient
) : RecipeApi {
    override suspend fun fetchRecipes(): RecipeListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun fetchDetails(recipeId: String): RecipeResponse {
        TODO("Not yet implemented")
    }
}
