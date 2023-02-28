package com.e.five_recipes.repositories

import android.util.Log
import com.e.five_recipes.database.dao.RecipeDao
import com.e.five_recipes.database.entity.DbRecipe
import com.e.five_recipes.database.entity.DbStep
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.models.toDbIngredient
import com.e.five_recipes.models.toDbRecipe
import com.e.five_recipes.models.toRecipe
import com.e.five_recipes.networking.RecipeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface RecipeRepository {
    fun fetchRecipes(): Flow<List<Recipe>>
    fun fetchRecipeById(recipeId: String): Flow<Recipe>
    fun fetchFavouriteRecipes(): Flow<List<Recipe>>
    fun removeRecipeFromFavourites(recipeId: String)
    fun fetchMyRecipes(): Flow<List<Recipe>>
    fun addRecipeToFavourites(recipe: Recipe)
    fun checkIfFavourite(recipeId: String): Flow<List<String>>
    fun addMyRecipe(recipe: Recipe)
}


internal class RecipeRepositoryImpl(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override fun fetchRecipes(): Flow<List<Recipe>> =
        flow {
            emit(
                recipeApi.fetchRecipes().recipes.map {
                    it.toRecipe()
                }
            )
        }

    override fun fetchRecipeById(recipeId: String): Flow<Recipe> =
        flow {
            emit(
                recipeApi.fetchDetails(recipeId).toRecipe()
            )
        }

    override fun fetchFavouriteRecipes(): Flow<List<Recipe>> =
        recipeDao.getFavouriteRecipes().map { list -> list.map { it.toRecipe(true) } }


    override fun removeRecipeFromFavourites(recipeId: String) {
        recipeDao.deleteRecipe(recipeId)
    }

    override fun fetchMyRecipes(): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun addRecipeToFavourites(recipe: Recipe) {
        val ingredients = recipe.ingredients
        val steps = recipe.steps

        Log.i("RECIPE", "Added recipe to favorites with id: " + recipe.id)

        recipeDao.insertRecipe(recipe.toDbRecipe(true))     //insert recipe

        ingredients.forEach {
            recipeDao.insertIngredient(it.toDbIngredient())
        }

        steps.forEach {
            recipeDao.insertStep(DbStep(0, it))
        }
    }

    override fun checkIfFavourite(recipeId: String) =
        recipeDao.getFavouriteRecipeById(recipeId)


    override fun addMyRecipe(recipe: Recipe) {
//        val ingredients = recipe.ingredients
//        val steps = recipe.steps
//
//        recipeDao.insertMyRecipe(recipe.toDbRecipe(true))     //insert recipe
//
//        ingredients.forEach {
//            recipeDao.insertIngredient(it.toDbIngredient())
//        }
//
//        steps.forEach {
//            recipeDao.insertStep(DbStep(0, it))
//        }
    }
}
