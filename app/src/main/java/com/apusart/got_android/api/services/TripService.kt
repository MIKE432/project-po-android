package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Trip
import com.apusart.got_android.api.tools.Test
import kotlinx.coroutines.delay


class TripService {

    suspend fun getTrips(): Resource<List<Trip>> {
        return Resource.success(Test.trips)
    }

    suspend fun getTrip(tripId: Int): Resource<Trip> {
        return Resource.success(Test.trips[0])
    }

    suspend fun joinTrip(tripId: Int, userId: Int): Resource<Boolean> {
        delay(2999)
        return Resource.success(true)
    }
}