package com.e.five_recipes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.e.five_recipes.R
import com.e.five_recipes.ui.bottomNav.BottomNavItem
import com.e.five_recipes.ui.components.FavouritesList
import com.e.five_recipes.ui.components.RecipesList
import com.e.five_recipes.ui.theme.DarkGreen


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: (id: String) -> Unit
) {
    val navController = rememberNavController() // nav controller for recipes list and favourites

    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(DarkGreen),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    Modifier
                        .padding(5.dp)
                        .height(36.dp)
                )
            }
        },
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            HomeNavigationGraph(
                navController = navController,
                navigateToDetails = navigateToDetails,
            )
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyRecipes,
        BottomNavItem.Favourites
    )

    BottomNavigation(
        contentColor = Color.Green,
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.screen_route,
                selectedContentColor = DarkGreen,
                unselectedContentColor = Color.DarkGray.copy(0.4f),
                icon = {
                    Image(
                        painter = if (currentRoute == screen.screen_route) painterResource(id = screen.selectedIcon) else painterResource(
                            screen.unselectedIcon
                        ),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 8.sp
                    )
                },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(screen.screen_route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    navigateToDetails: (String) -> Unit,
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            RecipesList(navigateToDetails)
        }
        composable(BottomNavItem.Favourites.screen_route) {
            FavouritesList(navigateToDetails)
        }
        composable(BottomNavItem.MyRecipes.screen_route) {
            MyRecipesScreen(navigateToDetails)
        }

    }
}


