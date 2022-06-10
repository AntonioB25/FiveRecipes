package com.e.five_recipes.ui.screens

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient
import com.e.five_recipes.timer.TimerService
import com.e.five_recipes.ui.components.IngredientsBox
import com.e.five_recipes.ui.components.LoadingSpinner
import com.e.five_recipes.viewModels.CalculatorViewModel
import com.e.five_recipes.viewModels.RecipesViewModel
import org.koin.androidx.compose.getViewModel
import java.util.stream.Collectors

var refIng: Ingredient? = null

@Composable
fun CalculatorScreen(
    recipeId: String
) {

    val recipesViewModel = getViewModel<RecipesViewModel>()
    val calculatorViewModel = getViewModel<CalculatorViewModel>()
    var amount by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    val calculatedIngrs = calculatorViewModel.calculatedIngredients.collectAsState()
    val mContext = LocalContext.current
    var calculatedIngredients = emptyList<Ingredient>()


    recipesViewModel.getDetails(recipeId)
    val recipe = recipesViewModel.recipeDetails.collectAsState().value
    val realIngredients = recipe?.ingredients

    if (realIngredients != null) {
        fun validateInput(userInput: String) {
            try {
                userInput.toDouble()
            } catch (e: NumberFormatException) {
                isError = true
            }
            isError = false
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.label_ingred_calculator), style = MaterialTheme.typography.h1)
            IngredientBoxes(
                realIngredients = realIngredients,
                calculatedIngredients = calculatedIngrs.value
            )

            DropDownMenu(ingredients = realIngredients)

            OutlinedTextField(
                value = amount,
                onValueChange = {
                    amount = it
                    isError = false
                },
                trailingIcon = {
                    if (isError)
                        Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colors.error)
                },
                singleLine = true,
                isError = isError,
                label = { Text(stringResource(R.string.label_amount)) },
                keyboardActions = KeyboardActions(
                    onAny = {
                        Log.i("Validate", "TREBAO BI SAD IĆI VALIDIRAT")
                        validateInput(amount)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            if (isError) {
                Text(
                    text = "Error message",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                enabled = refIng != null && amount.isNotEmpty(),
                onClick = {
                    //TODO calculate and show
                    Log.i(
                        "INGREDIENT",
                        "INGREDIETN IS: " + refIng.toString() + " AND VALUE IS: " + amount
                    )

                    test(calculatorViewModel, mContext)

                    calculatorViewModel.calculateIngreditents(
                        originalIngredients = realIngredients,
                        sastojak = refIng,
                        userValue = amount.toDouble(),
                    )


                    Log.i("INGREDIENT", "Calculated ingredients IS: ${calculatedIngrs.value}")
                }
            ) {
                Text(text = stringResource(R.string.label_calculate))
            }

            Button(
                onClick = {
                /*TODO add to database -> my recipes
                *  show bottom sheet for name and save*/
                }) {
                Text(text = stringResource(R.string.button_save_recipe))
            }
        }


    } else {
       LoadingSpinner()
    }

}


fun test(calculatorViewModel: CalculatorViewModel, context: Context) {
   //calculatorViewModel.startTimer(2000,context)
}

@Composable
fun IngredientBoxes(
    modifier: Modifier = Modifier,
    realIngredients: List<Ingredient>,
    calculatedIngredients: List<Ingredient>
) {

    Column(
        modifier = Modifier.padding(top=10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.label_original), style = MaterialTheme.typography.h3)
        IngredientsBox(
            Modifier
                .fillMaxWidth(0.95f), ingredients = realIngredients
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.label_calculated_ingred), style = MaterialTheme.typography.h3)
        IngredientsBox(
            Modifier
                .fillMaxWidth(0.95f), ingredients = calculatedIngredients
        )
    }

}

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    ingredients: List<Ingredient>
) {

    var ingredientNames =
        ingredients.stream().map { it.name.lowercase() }.collect(Collectors.toList())
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier
            .padding(20.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(R.string.label_choose_ingred)) },
            trailingIcon = {
                Icon(icon, null,
                    Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .requiredSizeIn(maxHeight = 150.dp)
        ) {
            ingredientNames.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                    refIng =
                        ingredients.first { it.name.lowercase() == selectedText.lowercase() } // SET REF INGREDINET
                    //TODO: set referential ingredient
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
