package com.apusart.got_android.api.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Trip
import com.apusart.got_android.api.services.TripService

class TripRepository {
    private val service = TripService()

    fun getTrips(): LiveData<Resource<List<Trip>>> {
        return liveData {
            emit(Resource.pending())
            emit(service.getTrips())
        }
    }

    suspend fun getTrip(tripId: Int): Resource<Trip> {
        return service.getTrip(tripId)
    }

    suspend fun joinTrip(tripId: Int): Resource<Unit> {
        val userId = 3
        return service.joinTrip(tripId, userId)
    }
}