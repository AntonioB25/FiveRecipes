package com.e.five_recipes

import android.app.Application
import com.e.five_recipes.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelsModule,
                    databaseModule,
                    httpClientModule,
                    recipeApiModule,
                    repositoryModules
                )
            )
        }

    }
}
