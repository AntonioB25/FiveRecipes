package com.e.five_recipes.ui.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.e.five_recipes.R
import com.e.five_recipes.ui.theme.DarkGreen
import com.e.five_recipes.ui.theme.RobotoCondensed
import com.e.five_recipes.viewModels.RecipeDetailsViewModel
import com.e.five_recipes.viewModels.RecipesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RecipeDetails(
    modifier: Modifier = Modifier,
    recipeId: String,
    navigateToCalculator: (String) -> Unit
) {
    val recipesViewModel = getViewModel<RecipesViewModel>()
    val recipeDetailsViewModel = getViewModel<RecipeDetailsViewModel>()
    recipesViewModel.getDetails(recipeId)
    val recipe = recipesViewModel.recipeDetails.collectAsState().value


    if (recipe != null) {
        val isDialogOpen = remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp)) //TODO extract
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp)) //TODO extract

            Box() {
                Image(
                    painter = rememberAsyncImagePainter(recipe.imagePath),
                    //painter = painterResource(id = R.drawable.dish_image),
                    null,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                FavouriteButton(
                    modifier = Modifier.align(TopEnd),
                    recipe = recipe
                )
            }

            val timer = recipeDetailsViewModel._timer.collectAsState()
            val timerTitle = recipeDetailsViewModel._timerTitle.collectAsState()


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.label_summary),
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = recipe.summary, fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Center
            )

            if (timer.value != null) {

                // Declaring circle radius
                val radius = 200f

                // Creating animation
                val animateFloat = remember { Animatable(1f) }

                val flag = remember { mutableStateOf(false)}

                //val animateFloat = recipeDetailsViewModel._animeFloat.collectAsState().value
                LaunchedEffect(key1 = animateFloat, key2 = flag) {
                    animateFloat.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                    // animateFloat.snapTo(0f)
                    // flag.value = !flag.value
                }
                // Creating Arc with useCenter as False
                androidx.compose.material.Surface( modifier = Modifier.fillMaxSize()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(200.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(timerTitle.value, style = MaterialTheme.typography.h2)
                            Text(
                                text = timer.value!!,
                                fontSize = 26.sp,
                                color = DarkGreen,
                                style = MaterialTheme.typography.h3,
                                fontWeight = FontWeight.Thin
                            )
                        }
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
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navigateToCalculator(recipeId) },
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = stringResource(R.string.label_calculator).uppercase())
                }
                Spacer(modifier = Modifier.width(5.dp))

                CallTimerDialog()
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.label_ingredients),
                style = MaterialTheme.typography.h3
            )

            IngredientsBox(
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                //.background(Color(224, 240, 212, 255)),
                ingredients = recipe.ingredients
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(R.string.label_steps), style = MaterialTheme.typography.h3)
            StepsBox(
                Modifier
                    .fillMaxSize()
                    .background(DarkGreen.copy(0.25f))
                    .padding(10.dp),
                steps = recipe.steps
            )
        }
    } else {
        LoadingSpinner()
    }
}


@Composable
fun StepsBox(
    modifier: Modifier = Modifier,
    steps: List<String>
) {
    Box(
        modifier = modifier
    ) {
        Log.i("SIZE", steps.size.toString())
        Column() {
            repeat(steps.size) {
                Log.i("SIZE", "UNUTRA SAM")
                StepBar(index = it, text = steps[it])
                Spacer(modifier = Modifier.height(5.dp))    //TODO extract
            }
        }
//        LazyColumn {
//            itemsIndexed(steps) { index, step ->
//                StepBar(index = index, text = step)
//                Spacer(modifier = Modifier.height(5.dp))    //TODO extract
//            }
//        }
    }
}

@Composable
fun StepBar(
    modifier: Modifier = Modifier,
    index: Int,
    text: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp),
        elevation = 2.dp
    ) {
        Column(Modifier.padding(start = 10.dp, end = 10.dp, top = 2.dp)) {
            Text(
                modifier = Modifier.drawBehind {
                    drawCircle(DarkGreen.copy(0.9f), this.size.height / 2)
                },
                text = (index + 1).toString(),
                fontFamily = RobotoCondensed,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(text = text, fontFamily = RobotoCondensed, fontSize = 16.sp, color = Color.Gray)
        }


    }
}



