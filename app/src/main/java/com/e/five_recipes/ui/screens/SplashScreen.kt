package com.e.five_recipes.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.e.five_recipes.R
import com.e.five_recipes.ui.bottomNav.BottomNavItem
import com.e.five_recipes.ui.theme.DarkGreen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController
) {
    var startAnimation by remember { mutableStateOf(false)}
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 1200
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(1000)
        navController.popBackStack()
        navController.navigate(BottomNavItem.Home.screen_route)
    }

    Splash(alphaAnim.value)
}


@Composable
fun Splash(
    alpha: Float
) {
    Box(
        modifier = Modifier
            .background(DarkGreen)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .alpha(alpha),
            painter = painterResource(id = R.drawable.logo_and_text_transparent),
            tint = Color.White,
            contentDescription = null
        )
    }
}
