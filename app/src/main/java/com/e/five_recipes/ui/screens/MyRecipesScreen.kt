package com.e.five_recipes.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.e.five_recipes.ui.theme.DarkGreen

@Composable
fun MyRecipesScreen(
    navigateToDetails: (String) -> Unit,
) {
    val context = LocalContext.current

    Scaffold(topBar = { },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(
                        context,
                        "Add recipe",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                shape = CircleShape,
                backgroundColor = DarkGreen,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "Add recipe", tint = Color.White)
            }
        }, content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "My recipes list is empty",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2,
                    color = Color.Gray
                )


            }
        })


}
