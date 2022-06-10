package com.e.five_recipes.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.viewModels.RecipesViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouritesList(
    navigateToDetails: (String) -> Unit,
) {

    val recipesViewModel = getViewModel<RecipesViewModel>()
    val favouriteRecipes = recipesViewModel.favouriteRecipes.collectAsState()

    if(favouriteRecipes.value.isNotEmpty()){
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp),
            cells = GridCells.Adaptive(minSize = 128.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(favouriteRecipes.value) { recipe ->
                FavouriteRecipeCard(
                    modifier = Modifier
                        .size(150.dp),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.card_clip)),
                    elevation = dimensionResource(R.dimen.card_elevation),
                    recipe = recipe,
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 10.dp).fillMaxSize()
        ) {
            Text(text = "Favourites list is empty", textAlign = TextAlign.Center, style = MaterialTheme.typography.h2, color = Color.Gray)
        }

    }
}


@Composable
fun FavouriteRecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    shape: Shape,
    elevation: Dp,
    navigateToDetails: (String) -> Unit
) {

    Card(
        shape = shape,
        elevation = elevation,
        modifier = modifier
            .wrapContentWidth()
            .padding(bottom = 10.dp)
            .clickable {
                navigateToDetails(recipe.id)
            }) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                //Image(painter = rememberAsyncImagePainter(model = recipe.imagePath), contentDescription = null )
                Log.i("RECEPT: ", "PUTANJA: ${recipe.imagePath}")
                Image(
                    painter = rememberAsyncImagePainter(recipe.imagePath),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
                FavouriteButton(recipe = recipe, modifier = Modifier.align(Alignment.TopEnd))
            }
        }
    }

}
