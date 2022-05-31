package com.e.five_recipes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.e.five_recipes.R
import com.e.five_recipes.models.Recipe

@Composable
fun RecipeCard(
    modifier: Modifier,
    recipe: Recipe
) {
    Row(
        modifier = modifier
    ) {
        Image(painter = painterResource(id = R.drawable.dish_image), contentDescription = null)
        Column {
            Text(text = recipe.name, Modifier.align(CenterHorizontally))
            Text(text = recipe.summary)
        }
    }
}




