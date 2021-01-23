package com.apusart.got_android.ui.trip_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Trip
import com.apusart.got_android.api.repositories.TripRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class TripDetailsViewModel: ViewModel() {
    private val tripRepository = TripRepository()
    val trip = MutableLiveData<Resource<Trip>>()
    val joined = MutableLiveData<Resource<Unit>>()


    fun getTrip(tripId: Int) {
        viewModelScope.launch {
            try {
                trip.value = Resource.pending()
                trip.value = tripRepository.getTrip(tripId)
            } catch (e: Exception) {
                trip.value = Resource.error(e.message)
            }
        }
    }

    fun joinTrip() {
        viewModelScope.launch {
            try {
                joined.value = Resource.pending()
                val tripId = trip.value?.data?.id ?: throw Exception("Nie można dołączyć")
                joined.value = tripRepository.joinTrip(tripId)
            } catch (e: Exception) {
                joined.value = Resource.error(e.message)
            }
        }
    }
}