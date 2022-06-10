package com.e.five_recipes.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step")
data class DbStep(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "step_id")
    val stepId: Int,
    @ColumnInfo(name = "text")
    val text: String
)

