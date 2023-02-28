package com.e.five_recipes.module

import androidx.room.Room
import com.e.five_recipes.database.AppDatabase
import com.e.five_recipes.database.dao.RecipeDao
import com.e.five_recipes.networking.ApiModule
import com.e.five_recipes.networking.RecipeApi
import com.e.five_recipes.networking.RecipeApiImpl
import com.e.five_recipes.repositories.RecipeRepositoryImpl
import com.e.five_recipes.repositories.RecipeRepository
import com.e.five_recipes.viewModels.CalculatorViewModel
import com.e.five_recipes.viewModels.RecipeDetailsViewModel
import com.e.five_recipes.viewModels.RecipesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        CalculatorViewModel()
    }
    viewModel {
        RecipesViewModel(get(), androidContext())
    }
    viewModel {
        RecipeDetailsViewModel()
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "movie-database"
        ).build()
    }

    single<RecipeDao> {
        val database = get<AppDatabase>()
        database.recipeDao()
    }
}

val httpClientModule = module {
    single { ApiModule.httpClient }
}

val recipeApiModule = module {
    single { RecipeApiImpl(get()) }
    single<RecipeApi> { RecipeApiImpl(get()) }
}

val repositoryModules = module {
    single { RecipeRepositoryImpl(get(), get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(get(), get()) }
}
