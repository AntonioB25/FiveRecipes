package com.e.five_recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.e.five_recipes.R
import com.e.five_recipes.models.Ingredient


@Composable
fun NewRecipeScreen() {
    // Declare a list of ingredients
    var title by remember { mutableStateOf("") }
    val ingredients = remember { mutableStateListOf(IngredientObj()) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        
        TextField(
            value = title,
            onValueChange = {newTitle -> title = newTitle},
            label = { Text(stringResource(R.string.recipe_title_label)) }
        )
        
        // Display the list of ingredients using a LazyColumn
        LazyColumn {
            items(ingredients) { ingredient ->
                IngredientInput(
                    ingredient = ingredient,
                    onIngredientChanged = { newIngredient ->
                        // Update the ingredient in the list when the text is changed
                        val index = ingredients.indexOf(ingredient)
                        ingredients[index] = newIngredient
                    },
                    onDeleteClicked = {
                        // Remove the ingredient from the list when the delete button is clicked
                        ingredients.remove(ingredient)
                    }
                )
            }
        }

        // Add a button to dynamically add new ingredients
        Button(
            onClick = { ingredients.add(IngredientObj()) },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add ingredient")
        }
    }
}

@Composable
fun IngredientInput(
    ingredient: IngredientObj,
    onIngredientChanged: (IngredientObj) -> Unit,
    onDeleteClicked: () -> Unit
) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
    ) {
        TextField(
            value = ingredient.name,
            onValueChange = { newName ->
                onIngredientChanged(ingredient.copy(name = newName))
            },
            label = { Text(stringResource(R.string.label_ingredient_name)) },
            modifier = Modifier.weight(1f)
        )

        TextField(
            value = ingredient.amount,
            onValueChange = { newAmount ->
                onIngredientChanged(ingredient.copy(amount = newAmount))
            },
            label = { Text(stringResource(R.string.label_ingredient_amount)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f)
        )

        TextField(
            value = ingredient.unit,
            onValueChange = { newUnit ->
                onIngredientChanged(ingredient.copy(unit = newUnit))
            },
            label = { Text(stringResource(R.string.label_ingredient_unit)) },
            modifier = Modifier.weight(1f)
        )

        // Add a delete button to remove the ingredient
        IconButton(
            onClick = onDeleteClicked,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}

data class IngredientObj(
    var name: String = "",
    var amount: String = "",
    var unit: String = ""
)
