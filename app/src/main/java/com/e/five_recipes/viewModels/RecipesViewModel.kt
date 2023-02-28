package com.e.five_recipes.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.five_recipes.models.Recipe
import com.e.five_recipes.models.RecipeResponse
import com.e.five_recipes.models.toRecipe
import com.e.five_recipes.observeconectivity.ConnectivityObserver
import com.e.five_recipes.observeconectivity.NetworkConnectivityObserver
import com.e.five_recipes.repositories.RecipeRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val recipesRepository: RecipeRepository,
    private val context: Context // TODO: fix
) : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    val allRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeDetails = MutableStateFlow<Recipe?>(null)

    val connectivityObserver = NetworkConnectivityObserver(context = context)

    init {
        getRecipes()
        //getAllRecipes()
        getFavourites()
        //getMyRecipes()
    }

    private fun getRecipes() {
        val recipes: MutableList<RecipeResponse> = mutableListOf()
        db.collection("recipes").get().addOnSuccessListener() {
            viewModelScope.launch {
                for (snapshot in it) {
                    //Log.i("RECEPTI", "SNAP: ${snapshot}")
                    val recipeData = snapshot.toObject(
                        RecipeResponse::class.java
                    )
                    //Log.i("RECEPTI", recipeData.imageURL)
                    recipes.add(
                        RecipeResponse(
                            snapshot.id,
                            recipeData.name,
                            recipeData.summary,
                            recipeData.imageURL,
                            recipeData.ingredients,
                            recipeData.steps
                        )
                    )
                }
                allRecipes.emit(recipes.map { it.toRecipe() })
            }
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }


    fun getDetails(recipeId: String) {
        db.collection("recipes").document(recipeId).get().addOnSuccessListener() {
            viewModelScope.launch {
                val recipe = it.toObject(
                    RecipeResponse::class.java
                )?.toRecipe()

                Log.i("RECEPTI: ID: ", it.id)
                if (recipe != null) {
                    recipeDetails.emit(
                        Recipe(
                            it.id,
                            recipe.name,
                            recipe.summary,
                            recipe.imagePath,
                            recipe.ingredients,
                            recipe.steps
                        )
                    )
                }
            }
        }
            .addOnFailureListener {
                Log.e("firebase", "Error geting data", it)
            }
    }


    //TODO: change to allRecipes when you remove old
    val ALLRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val favouriteRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val myRecipes = MutableStateFlow<List<Recipe>>(emptyList())


    private fun getAllRecipes() {
        viewModelScope.launch {
            recipesRepository.fetchRecipes().collect { ALLRecipes.emit(it) }
        }
    }

    private fun getFavourites(){
        viewModelScope.launch {
            recipesRepository.fetchFavouriteRecipes().collect(){favouriteRecipes.emit(it)}
        }
    }

    private fun getMyRecipes(){
        viewModelScope.launch {
            recipesRepository.fetchMyRecipes().collect(){myRecipes.emit(it)}
        }
    }


    fun addRecipeToFavourites(recipe: Recipe){
        CoroutineScope(Dispatchers.IO).launch {
            recipesRepository.addRecipeToFavourites(recipe)
        }
    }

    fun removeFromFavourites(recipeId: String){
        CoroutineScope(Dispatchers.IO).launch {
            recipesRepository.removeRecipeFromFavourites(recipeId)
        }
    }


    val isFavourite = MutableStateFlow(false)

    fun checkIfFavourite(recipeId: String){
        viewModelScope.launch {
            Log.i("TESTTEST", "DOHVATIO SAM LISTU: ${recipesRepository.checkIfFavourite(recipeId)}")
            recipesRepository.checkIfFavourite(recipeId).collect(){
                isFavourite.emit(it.isNotEmpty())
            }
        }
    }




}

