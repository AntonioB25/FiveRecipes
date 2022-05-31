package com.e.five_recipes.ui.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var selectedIcon: ImageVector,
    var unselectedIcon: ImageVector,
    var screen_route: String
) {
    object Home : BottomNavItem(
        "Home",
        Icons.Default.Home,
        Icons.Outlined.Home,
        "home"
    )

    object Favourites : BottomNavItem(
        "Favourites",
        Icons.Default.Favorite,
        Icons.Default.FavoriteBorder,
        "favourites"
    )
}
