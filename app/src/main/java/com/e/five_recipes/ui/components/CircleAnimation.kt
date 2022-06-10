package com.e.five_recipes.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.e.five_recipes.ui.theme.DarkGreen

@Composable
fun CircleAnimation() {


    // Creating a Simple Scaffold
    // Layout for the application
    Scaffold(

        // Creating a Top Bar
        topBar = {
            TopAppBar(
                title = { Text("GFG | Circle Animation", color = Color.White) },
                backgroundColor = Color(0xff0f9d58)
            )
        },

        // Creating Content
        content = {

            // Creating a Column Layout
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Declaring circle radius
                val radius = 200f

                // Creating animation
                val animateFloat = remember { Animatable(0f) }
                val infiniteTransition = rememberInfiniteTransition()

                LaunchedEffect(animateFloat) {
                    animateFloat.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                }
                // Creating Arc with useCenter as False
               androidx.compose.material.Surface( modifier = Modifier.fillMaxSize()) {
                   Box(
                       contentAlignment = Alignment.Center,
                       modifier = Modifier.size(200.dp)
                   ) {
                       Text(text = "Alo", textAlign = TextAlign.Center)
                       Canvas(modifier = Modifier.size(125.dp)) {
                           drawArc(
                               color = DarkGreen,
                               startAngle = 0f,
                               sweepAngle = 360f * animateFloat.value,
                               useCenter = false,
                               size = Size(radius * 2, radius * 2),
                               style = Stroke(2.0f)
                           )
                       }
                   }
               }

            }
        }
    )
}
