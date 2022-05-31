package com.e.five_recipes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.models.Recipe

@Composable
fun RecipeDetails(
    modifier: Modifier = Modifier,
    recipeId: String
) {

    var recipe = Recipe(
        "1", "recept1", "kratki opis je ovo he he heh eh he", "path",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    )

    Column {
        Text(text = recipe.name)
        Image(painterResource(R.drawable.dish_image), null)

        Text(text = stringResource(R.string.label_summary))
        Text(text = recipe.summary)

        Text(text = stringResource(R.string.label_ingredients))
        LazyColumn {
            items(recipe.ingredients) { ingredient ->
                IngredientBar(ingredient = ingredient)
            }
        }

        Text(text = stringResource(R.string.label_steps))
        LazyColumn {
            items(recipe.steps) { step ->
                StepBar(stepText = step)
            }
        }
    }
}


@Composable
fun IngredientBar(
    modifier: Modifier = Modifier,
    ingredient: Ingredient
) {
    Row {
        Text(text = ingredient.name)
        Text(text = ingredient.quantity.toString())
        Text(text = ingredient.unit)
    }
}

@Composable
fun StepBar(
    modifier: Modifier = Modifier,
    stepText: String
) {

    Text(text = stepText)
}
