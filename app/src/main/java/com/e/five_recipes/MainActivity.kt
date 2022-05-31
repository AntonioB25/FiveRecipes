package com.e.five_recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.ui.screens.HomeScreen
import com.e.five_recipes.ui.theme.FiveRecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiveRecipesTheme {

                val recipe = Recipe(
                    1, "recept1", "kratki opis je ovo he he heh eh he", "path",
                    listOf(
                        Ingredient("id", "Mlijeko", 2.0, "dl"),
                        Ingredient("id1", "Mlijeko", 2.0, "dl"),
                        Ingredient("id2", "Mlijeko", 2.0, "dl")
                    ),
                    listOf("Step1", "step2")
                )

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(modifier = Modifier)
                    //RecipeDetails(recipe = recipe)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FiveRecipesTheme {
        Greeting("Android")
    }
}
