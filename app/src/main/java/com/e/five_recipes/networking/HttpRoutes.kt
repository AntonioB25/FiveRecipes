package com.e.five_recipes.networking

object HttpRoutes {
    const val BASE_URL = "https://api.themoviedb.org/3"
    const val RECIPES = "${BASE_URL}/recipe/popular"
    const val RECIPE_DETAILS = "${BASE_URL}/recipe/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
}
