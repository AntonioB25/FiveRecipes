package com.e.five_recipes.ui.components

import android.content.Context
import androidx.compose.ui.graphics.Color
import android.net.*
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.observeconectivity.ConnectivityObserver
import com.e.five_recipes.viewModels.RecipesViewModel
import okhttp3.internal.notifyAll
import org.koin.androidx.compose.getViewModel
import androidx.compose.material.icons.outlined
import androidx.compose.material.icons.outlined.Warning


val recipes: List<Recipe> = listOf(
    Recipe(
        "1",
        "recept1",
        "kratki opis je ovo he he heh eh he",
        "https://www.wellplated.com/wp-content/uploads/2021/02/Best-Roasted-Asparagus-Recipe.jpg",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    )
)

@Composable
fun RecipesList(
    navigateToDetails: (String) -> Unit
) {

    val recipesViewModel = getViewModel<RecipesViewModel>()
    val recipes = recipesViewModel.allRecipes.collectAsState().value
    val status by recipesViewModel.connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

    if(status in setOf(
            ConnectivityObserver.Status.Unavailable,
            ConnectivityObserver.Status.Lost,
            ConnectivityObserver.Status.Losing
        )){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = "No internet connection",
                tint = Gray,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "No internet connection",
                style = MaterialTheme.typography.h6,
                color = Gray
            )
        }
    }
    if(recipes.isEmpty()){
        LoadingSpinner()
    }else{

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            
            items(recipes) { recipe ->
                RecipeCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.card_image_height)),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.card_clip)),
                    elevation = dimensionResource(R.dimen.card_elevation),
                    recipe = recipe,
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }
}
