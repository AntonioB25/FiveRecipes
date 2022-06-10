package com.e.five_recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.e.five_recipes.models.Ingredient

@Composable
fun IngredientsBox(
    modifier: Modifier = Modifier,
    ingredients: List<Ingredient>
) {
    Card(
        modifier = modifier,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            repeat(ingredients.size) {
                IngredientBar(ingredient = ingredients[it])
                Divider(color = Color.LightGray, thickness = 0.5.dp)
            }
//            items(ingredients) { ingredient ->
//                IngredientBar(ingredient = ingredient)
//                Divider(color = Color.LightGray, thickness = 0.5.dp)
//            }
        }
    }
}

@Composable
fun IngredientBar(
    modifier: Modifier = Modifier,
    ingredient: Ingredient
) {
    Row {

        Text(text = ingredient.name.lowercase(), fontSize = 16.sp, color = Color.DarkGray)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = String.format("%.1f", ingredient.quantity),
            fontSize = 16.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = ingredient.unit.lowercase(),
            fontSize = 16.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.End
        )
    }
}
