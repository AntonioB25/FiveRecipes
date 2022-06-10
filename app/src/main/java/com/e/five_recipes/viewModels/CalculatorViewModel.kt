package com.e.five_recipes.viewModels

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {

    val calculatedIngredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val _timer = MutableStateFlow("")

    init {

    }

    fun initIngr() {
        viewModelScope.launch {
            calculatedIngredients.emit(emptyList())
        }
    }

    fun calculateIngreditents(
        originalIngredients: List<Ingredient>,
        userValue: Double,
        //IDsastojka: String,
        sastojak: Ingredient?
    ): MutableList<Ingredient> {
        val originalValue =
            originalIngredients.first { it.name.lowercase() == sastojak?.name?.lowercase() }.quantity
        val coef = userValue / originalValue

        var newList = emptyList<Ingredient>().toMutableList();

        for (ingredient in originalIngredients) {
            newList.add(
                Ingredient(
                    ingredient.id,
                    ingredient.name,
                    ingredient.quantity * coef,
                    ingredient.unit
                )
            )
        }

        viewModelScope.launch {
            calculatedIngredients.emit(newList)
        }
        return newList
    }


}
