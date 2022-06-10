package com.e.five_recipes.database.entity

import androidx.room.*
import com.e.five_recipes.models.Ingredient

@Entity(tableName = "recipe")
data class DbRecipe(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "recipe_id")
    val recipeId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "summary")
    val summary: String,
    @ColumnInfo(name = "image_path")
    val imagePath: String,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean

)

data class RecipeWithIngredientsAndSteps(
    @Embedded val recipe: DbRecipe,
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

//@Entity(primaryKeys = ["recipe_id", "ingredient_id"])
//data class RecipeIngredientCrossRef(
//    @ColumnInfo(name = "recipe_id")
//    val recipeId: Int,
//    @ColumnInfo(name = "ingredient_id")
//    val ingredientId: String
//)
//
//@Entity(primaryKeys = ["recipe_id", "step_id"])
//data class RecipeStepCrossRef(
//    @ColumnInfo(name = "recipe_id")
//    val recipeId: Int,
//    @ColumnInfo(name = "step_id")
//    val stepId: String
//)
