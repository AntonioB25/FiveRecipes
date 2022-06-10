package com.e.five_recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient


@Composable
fun CreateRecipeForm(

) {
    var ingredientFieldCount by remember { mutableStateOf(1) }
    var steps by remember { mutableStateOf(listOf<String>()) }

    var ingredients by remember { mutableStateOf(listOf<Ingredient>()) }

    var name by remember { mutableStateOf("") }
    var summary by remember { mutableStateOf("") }
    var step by remember { mutableStateOf("") }


    Column {
        Text(text = stringResource(R.string.title_create_recipe))

        //recipe name
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            placeholder = { Text(stringResource(R.string.placeholder_name)) },
            label = { Text(stringResource(R.string.placeholder_name)) },
            onValueChange = {
                name = it
            }
        )

        //recipe summary
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = summary,
            placeholder = { Text(stringResource(R.string.placeholder_summary)) },
            label = { Text(stringResource(R.string.placeholder_summary)) },
            onValueChange = {
                summary = it
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        //IngredientInputBar()
        Spacer(modifier = Modifier.height(10.dp))
        StepInputBar()

        Button(
            onClick = {
                //TODO:
            }
        ) {
            Text(text = "Add")
        }




    }
}


@Composable
fun IngredientInputBar(
    modifier: Modifier = Modifier,
    ingredients: List<Ingredient>
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f),
                value = name,
                placeholder = { Text(stringResource(R.string.placeholder_name)) },
                label = { Text(stringResource(R.string.placeholder_name)) },
                onValueChange = {
                    name = it
                }
            )

            Spacer(modifier = Modifier.width(5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = amount,
                placeholder = { Text(stringResource(R.string.placeholder_amount)) },
                label = { Text(stringResource(R.string.placeholder_amount)) },
                onValueChange = {
                    amount = it
                }
            )
            Spacer(modifier = Modifier.width(5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = unit,
                placeholder = { Text(stringResource(R.string.placeholder_unit)) },
                label = { Text(stringResource(R.string.placeholder_unit)) },
                onValueChange = {
                    unit = it
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                //TODO: add ingredient to list
            }) {
            Text(text = "Add ingredient")
        }
    }
}

@Composable
fun StepInputBar(
    modifier: Modifier = Modifier
) {
    var step by remember { mutableStateOf("") }
    var counter by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f),
                value = step,
                placeholder = { Text(stringResource(R.string.label_step)) },
                label = { Text(stringResource(R.string.label_step)) },
                onValueChange = {
                    step = it
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                //TODO: add step to list
                counter++
            }) {
            Text(text = "Add step")
        }

        repeat(counter) {
            Text(text = "TEST")
        }
    }


}


@Composable
fun IngredientsList(
    ingredients: List<Ingredient>
) {
    IngredientsBox(ingredients = ingredients)
}
