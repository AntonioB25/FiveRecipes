package com.e.five_recipes.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.e.five_recipes.R
import com.e.five_recipes.models.Recipe

@Composable
fun RecipeCard(
    modifier: Modifier,
    shape: Shape,
    elevation: Dp,
    recipe: Recipe,
    navigateToDetails: (String) -> Unit
) {

    Log.i("IMAGE", "RECEPT SAM: ${recipe.id}")
    Card(
        shape = shape,
        elevation = elevation,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.card_margin))
    ) {
        Row(
            modifier = modifier
                .clickable { navigateToDetails(recipe.id) }
        ) {
            Image(
                painter = rememberAsyncImagePainter(recipe.imagePath),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(100.dp).fillMaxHeight(),
                alignment = Center

            )
            Column(
                Modifier.padding(start = dimensionResource(R.dimen.card_text_margin))
            ) {
                Text(
                    text = recipe.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = recipe.summary,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
