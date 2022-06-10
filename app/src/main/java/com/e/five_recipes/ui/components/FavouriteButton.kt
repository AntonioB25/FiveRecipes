package com.e.five_recipes.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.ui.theme.DarkGreen
import com.e.five_recipes.viewModels.RecipesViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    recipe: Recipe
) {
    //var isFavorite by remember { mutableStateOf(movie.isFavorite) }
   // var isFavorite by remember { mutableStateOf(recipe.isFavourite) }    //TODO view model function to check if is in database favourites

    val recipesViewModel = getViewModel<RecipesViewModel>()
    recipesViewModel.checkIfFavourite(recipe.id)
    var isFavorite = recipesViewModel.isFavourite.collectAsState().value

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            if (!isFavorite) {
                recipesViewModel.addRecipeToFavourites(recipe)
                isFavorite = true
            } else {
                isFavorite = false
               recipesViewModel.removeFromFavourites(recipe.id)
            }

            isFavorite = !isFavorite
        },
        modifier = modifier
            .clip(CircleShape)
            .background(DarkGreen.copy(0.7f))
            .padding(1.dp)
    ) {
        Icon(
            tint = color,
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

