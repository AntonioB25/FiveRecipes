package com.e.five_recipes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.five_recipes.database.dao.RecipeDao
import com.e.five_recipes.database.entity.*


@Database(
    entities = [
        DbStep::class,
        DbIngredient::class,
        DbRecipe::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
