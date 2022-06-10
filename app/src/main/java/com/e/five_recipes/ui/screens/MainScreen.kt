package com.e.five_recipes.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.e.five_recipes.ui.bottomNav.BottomNavItem
import com.e.five_recipes.ui.components.RecipeDetails


@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    navigateToDetails: (String) -> Unit,
    navigateToCalculator: (String) -> Unit
) {
//BottomNavItem.Home.screen_route
    NavHost(navController, startDestination = "splash_screen") {
        composable("splash_screen"){
            AnimatedSplashScreen(navController)
        }

        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(modifier = Modifier, navigateToDetails)
        }

        composable(
            "details/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            RecipeDetails(
                recipeId = navBackStackEntry.arguments?.getString("recipeId")!!,
                navigateToCalculator = navigateToCalculator
            )
        }

        composable(
            "calculator/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            CalculatorScreen(recipeId = navBackStackEntry.arguments?.getString("recipeId")!!)
        }
    }
}
