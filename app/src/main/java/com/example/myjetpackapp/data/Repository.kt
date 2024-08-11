package com.example.myjetpackapp.data

import com.example.myjetpackapp.model.Destinations
import com.example.myjetpackapp.model.DummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val favDestination = mutableListOf<Destinations>()

    init {
        if (favDestination.isEmpty()) {
            DummyData.data.forEach { destination ->
                favDestination.add(Destinations(destination))
            }
        }
    }

    fun getAllDestinations(): Flow<List<Destinations>> {
        return flowOf(favDestination)
    }

    fun getDestinationById(id: Int): Destinations {
        return favDestination.first {
            it.destinations.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}