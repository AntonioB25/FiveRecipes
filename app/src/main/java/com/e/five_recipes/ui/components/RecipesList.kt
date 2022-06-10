package com.e.five_recipes.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.viewModels.RecipesViewModel
import org.koin.androidx.compose.getViewModel

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
//    ),
//    Recipe(
//        "2", "Čokoladni kolači", "kratki opis je ovo he he heh eh he", "path",
//        listOf(
//            Ingredient("id", "Mlijeko", 2.0, "dl"),
//            Ingredient("id1", "Mlijeko", 2.0, "dl"),
//            Ingredient("id2", "Mlijeko", 2.0, "dl")
//        ),
//        listOf("Step1", "step2")
//    ),
//    Recipe(
//        "3", "recept1", "kratki opis je ovo he he heh eh he", "path",
//        listOf(
//            Ingredient("id", "Mlijeko", 2.0, "dl"),
//            Ingredient("id1", "Mlijeko", 2.0, "dl"),
//            Ingredient("id2", "Mlijeko", 2.0, "dl")
//        ),
//        listOf("Step1", "step2")
//    ),
//    Recipe(
//        "4", "recept1", "kratki opis je ovo he he heh eh he", "path",
//        listOf(
//            Ingredient("id", "Mlijeko", 2.0, "dl"),
//            Ingredient("id1", "Mlijeko", 2.0, "dl"),
//            Ingredient("id2", "Mlijeko", 2.0, "dl")
//        ),
//        listOf("Step1", "step2")
//    ),
//    Recipe(
//        "5", "recept1", "kratki opis je ovo he he heh eh he", "path",
//        listOf(
//            Ingredient("id", "Mlijeko", 2.0, "dl"),
//            Ingredient("id1", "Mlijeko", 2.0, "dl"),
//            Ingredient("id2", "Mlijeko", 2.0, "dl")
//        ),
//        listOf("Step1", "step2")
    )
)

@Composable
fun RecipesList(
    navigateToDetails: (String) -> Unit
) {
    val recipesViewModel = getViewModel<RecipesViewModel>()
    val recipes = recipesViewModel.allRecipes.collectAsState().value

    Log.i("RECEPTI", "RECEPTI $recipes")
   

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
