package com.e.five_recipes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.e.five_recipes.ui.components.CircleAnimation
import com.e.five_recipes.ui.components.NewRecipeScreen
import com.e.five_recipes.ui.components.ShowAlertDialog
import com.e.five_recipes.ui.screens.MainNavigationGraph
import com.e.five_recipes.ui.theme.FiveRecipesTheme
import com.e.five_recipes.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // main screen controller

            fun navigateToDetails(id: String) {
                navController.navigate("details/${id}")
            }

            fun navigateToCalculator(id: String) {
                navController.navigate("calculator/${id}")
            }
            FiveRecipesTheme {
                MainNavigationGraph(navController, ::navigateToDetails, ::navigateToCalculator)
                //CallAlertDialog()
                //NewRecipeScreen()
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


