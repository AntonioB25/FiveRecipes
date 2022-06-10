package com.e.five_recipes.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ingredient")
data class DbIngredient(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ingredient_id")
    val ingredientId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: Double,
    @ColumnInfo(name = "unit")
    val unit: String
)
