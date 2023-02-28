package com.e.five_recipes.observeconectivity

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {
    fun observe() : Flow<Status>

    enum class Status{
        Available, Unavailable, Losing, Lost
    }
}