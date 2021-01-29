package com.apusart.got_android.ui.join_trip.trips_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Trip

open class TripsAdapter(val navController: NavController): ListAdapter<Trip, TripViewHolder>(diffUtil) {
    object diffUtil: DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.trip_list_item, parent, false)

        return TripViewHolder((container))
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bind(getItem(position)) { id ->
            navController.navigate(TripsListFragmentDirections.actionTripsListFragmentToTripDetails(id))
        }
    }
}