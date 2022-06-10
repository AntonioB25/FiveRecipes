package com.e.five_recipes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.e.five_recipes.R

val RobotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_condensed_italic, style = FontStyle.Italic),
    Font(R.font.roboto_condensed_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.roboto_condensed_light, weight = FontWeight.Light),
    Font(R.font.roboto_condensed_regular),
    Font(R.font.roboto_condensed_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = RobotoCondensed,
        fontStyle = FontStyle.Italic,
        color = Color.DarkGray,
        fontSize = 32.sp
    ),

    h2 = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = RobotoCondensed,
        color = Color.Gray,
        fontSize = 22.sp
    ),
    body1 = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    )
