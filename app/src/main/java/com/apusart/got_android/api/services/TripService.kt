package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Trip
import com.apusart.got_android.api.models.performRequest
import com.apusart.got_android.api.tools.Test
import kotlinx.coroutines.delay


class TripService {
    private val service = RetrofitImpl.retrofit


    suspend fun getTrips(): Resource<List<Trip>> {
        return performRequest { service.getTrips() }
    }

    suspend fun getTrip(tripId: Int): Resource<Trip> {
        return performRequest { service.getTrip(tripId) }
    }

    suspend fun joinTrip(tripId: Int, userId: Int): Resource<Unit> {

        return performRequest { service.joinTrip(userId, tripId) }
    }
}