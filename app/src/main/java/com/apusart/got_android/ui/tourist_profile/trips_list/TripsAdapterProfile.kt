package com.apusart.got_android.ui.tourist_profile.trips_list

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.apusart.got_android.R
import com.apusart.got_android.ui.join_trip.trips_list.TripViewHolder
import com.apusart.got_android.ui.join_trip.trips_list.TripsAdapter

class TripsAdapterProfile(navController: NavController) : TripsAdapter(navController) {
    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bind(getItem(position)) { id ->
            val bundle = bundleOf("tripId" to id)
            navController.navigate(R.id.profileTripsDetailsFragment, bundle)
        }
    }
}