package com.e.five_recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.e.five_recipes.ui.theme.DarkGreen

@Composable
fun LoadingSpinner(

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = DarkGreen.copy(0.8f),
            strokeWidth = 2.dp,
            modifier = Modifier.height(50.dp).width(50.dp)
            )
    }
}
