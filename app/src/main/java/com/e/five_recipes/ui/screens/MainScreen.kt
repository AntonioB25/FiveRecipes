package com.e.five_recipes.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.e.five_recipes.ui.bottomNav.BottomNavItem
import com.e.five_recipes.ui.components.RecipeDetails


@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController() // main screen controller

    fun navigateToDetails(id: Int?) {
        navController.navigate("details/${id}")
    }

    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(Modifier, ::navigateToDetails)
        }

        composable(
            "details/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            RecipeDetails(recipeId = navBackStackEntry.arguments?.getString("recipeId")!!)
        }
    }
}
