package com.e.five_recipes.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.FullHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.e.five_recipes.R
import com.e.five_recipes.ui.theme.DarkGreen
import com.e.five_recipes.viewModels.CalculatorViewModel
import com.e.five_recipes.viewModels.RecipeDetailsViewModel
import com.e.five_recipes.viewModels.RecipesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ShowAlertDialog(isDialogOpen: MutableState<Boolean>) {
    val timerName = remember { mutableStateOf("") }
    var pickerValue by remember { mutableStateOf<Hours>(FullHours(0, 0)) }
    val recipeDetailsViewModel = getViewModel<RecipeDetailsViewModel>()
    val mContext = LocalContext.current

    val focusRequester = remember {
        FocusRequester
    }

    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(450.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = stringResource(R.string.label_set_timer),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_icon_clock),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    OutlinedTextField(
                        value = timerName.value,
                        onValueChange = { timerName.value = it },
                        label = { Text(text = stringResource(R.string.label_timer_name)) },
                        placeholder = { Text(text = stringResource(R.string.label_timer_name)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    HoursNumberPicker(
                        dividersColor = DarkGreen,
                        leadingZero = true,
                        value = pickerValue,
                        onValueChange = {
                            pickerValue = it
                        },
                        hoursDivider = {
                            Text(
                                modifier = Modifier.size(24.dp),
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.label_minutes)
                            )
                        },
                        minutesDivider = {
                            Text(
                                modifier = Modifier.size(24.dp),
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.label_seconds)
                            )
                        }
                    )

                    Button(
                        onClick = {
                            isDialogOpen.value = false
                            timer(pickerValue,timerName.value, recipeDetailsViewModel, mContext)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(DarkGreen)
                    ) {
                        Text(
                            text = stringResource(R.string.button_timer_start).uppercase(),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CallTimerDialog() {
    val isDialogOpen = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ShowAlertDialog(isDialogOpen)

        Button(
            onClick = {
                isDialogOpen.value = true
            },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = stringResource(R.string.button_timer).uppercase(),
                color = Color.White
            )
        }
    }
}


fun timer(duration: Hours, timerTitle: String, recipeDetailsViewModel: RecipeDetailsViewModel, context: Context) {
    val millis = (duration.hours * 60 + duration.minutes) * 1000L                  // hours are minutes, minutes are seconds
    Log.i("MILLIS", "MINUTES: " + duration.hours.toString())
    Log.i("MILLIS", "SECONDS: " + duration.minutes.toString())
    Log.i("MILLIS", "MIN AND SECS: " + (duration.hours * 60 + duration.minutes))

    recipeDetailsViewModel.startTimer(millis,context)
    recipeDetailsViewModel.setTimerTitle(timerTitle)
}

