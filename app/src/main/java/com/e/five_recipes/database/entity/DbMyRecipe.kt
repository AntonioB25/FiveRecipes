package com.e.five_recipes.database.entity

import androidx.room.*

@Entity(tableName = "my_recipe")
data class DbMyRecipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "summary")
    val summary: String,
    @ColumnInfo(name = "image_path")
    val imagePath: String,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean
)

data class MyRecipeWithIngredientsAndSteps(
    @Embedded val recipe: DbMyRecipe,
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "ingredient_id",
    )
    val ingredients: List<DbIngredient>,
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "step_id",
    )
    val steps: List<DbStep>,
)
