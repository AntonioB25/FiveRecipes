//package com.e.five_recipes.firebase
//
//import android.util.Log
//import com.e.five_recipes.models.RecipeResponse
//import com.google.firebase.firestore.FirebaseFirestore
//
//
//class Test {
//    val db = FirebaseFirestore.getInstance()
//
//
//    fun test(): List<RecipeResponse> {
//        var recipes: MutableList<RecipeResponse> = mutableListOf()
//
//        var flag = false
//        db.collection("recipes").get().result.documents
//
//        Log.i("firebase", "RESULT ${db.collection("recipes").get().result.documents}")
//
//        db.collection("recipes").get().addOnSuccessListener() {
//            for (snapshot in it) {
//                val recipeData = snapshot.toObject(
//                    RecipeResponse::class.java
//                )
//                recipes.add(
//                    RecipeResponse(
//                        snapshot.id,
//                        recipeData.name,
//                        recipeData.summary,
//                        recipeData.imagePath,
//                        recipeData.ingredients,
//                        recipeData.steps
//                    )
//                )
//            }
//            flag = true
//            Log.i("firebase", "RECEPTI $recipes")
//        }.addOnFailureListener {
//            Log.e("firebase", "Error getting data", it)
//            flag = true
//        }
//
//        return recipes
//    }
//
//}
