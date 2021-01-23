package com.apusart.got_android.ui.join_trip.trips_list

import androidx.lifecycle.ViewModel
import com.apusart.got_android.api.repositories.TripRepository

class TripListViewModel: ViewModel() {
    private val tripRepository = TripRepository()
    val trips = tripRepository.getTrips()
}