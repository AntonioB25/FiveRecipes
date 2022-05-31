package com.e.five_recipes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.ui.components.RecipeCard

val recipes: List<Recipe> = listOf(
    Recipe(
        "1", "recept1", "kratki opis je ovo he he heh eh he", "path",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    ),
    Recipe(
        "2", "recept1", "kratki opis je ovo he he heh eh he", "path",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    ),
    Recipe(
        "3", "recept1", "kratki opis je ovo he he heh eh he", "path",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    ),
    Recipe(
        "4", "recept1", "kratki opis je ovo he he heh eh he", "path",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    ),
    Recipe(
        "5", "recept1", "kratki opis je ovo he he heh eh he", "path",
        listOf(
            Ingredient("id", "Mlijeko", 2.0, "dl"),
            Ingredient("id1", "Mlijeko", 2.0, "dl"),
            Ingredient("id2", "Mlijeko", 2.0, "dl")
        ),
        listOf("Step1", "step2")
    )
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit
) {

    Scaffold(
        topBar ={
            Box{
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(),
                contentAlignment = Alignment.Center
                ) {
                Image(
                    painter = painterResource(id = androidx.compose.foundation.layout.R.drawable.logo),
                    contentDescription = "App logo",
                    Modifier
                        .height(34.dp)
                        .width(136.dp)
                )
            }
            }
        }
    ) {

    }
    

}



//LazyColumn {
//    items(recipes) { recipe ->
//        RecipeCard(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(dimensionResource(R.dimen.card_margin))
//                .border(dimensionResource(R.dimen.card_border_width), Color.Red)
//                .height(dimensionResource(R.dimen.card_image_height)),
//            recipe = recipe
//        )
//    }
//}
