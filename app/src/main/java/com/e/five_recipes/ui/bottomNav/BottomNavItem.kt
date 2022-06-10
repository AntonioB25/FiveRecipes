package com.e.five_recipes.ui.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.e.five_recipes.R

sealed class BottomNavItem(
    var title: String,
    var selectedIcon: Int,
    var unselectedIcon: Int,
    var screen_route: String
) {
    object Home : BottomNavItem(
        "Home",
        R.drawable.ic_home_filled,
        R.drawable.ic_outline_home_24,
        "home"
    )

    object Favourites : BottomNavItem(
        "Favourites",
        R.drawable.ic_baseline_favorite_24,
        R.drawable.ic_baseline_favorite_border_24,
        "favourites"
    )

    object MyRecipes : BottomNavItem(
        "My Recipes",
        R.drawable.ic_food_bank_filled,
        R.drawable.ic_food_bank_outlined,
        "myRecipes",

    )
}
