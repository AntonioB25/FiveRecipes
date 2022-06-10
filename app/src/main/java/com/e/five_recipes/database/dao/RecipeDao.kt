package com.e.five_recipes.database.dao

import androidx.room.*
import com.e.five_recipes.database.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: DbRecipe)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMyRecipe(recipe: DbMyRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredient(ingredient: DbIngredient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStep(step: DbStep)

    // DELETE
    @Query("DELETE FROM recipe WHERE recipe_id =:recipeId")
    fun deleteRecipe(recipeId: String)

    // GET FAVOURITES
    @Query("SELECT * FROM recipe")
    fun getFavouriteRecipes(): Flow<List<RecipeWithIngredientsAndSteps>>

//    @Query("SELECT * FROM my_recipe")
//    fun getMyRecipes(): Flow<List<MyRecipeWithIngredientsAndSteps>>


    @Query("SELECT recipe_id FROM recipe WHERE recipe_id=:recipeId AND is_favourite = 1")
    fun getFavouriteRecipeById(recipeId: String): Flow<List<String>>

}
