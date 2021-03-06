package com.apusart.got_android.ui.join_trip.trips_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apusart.got_android.api.models.Trip
import kotlinx.android.synthetic.main.trip_list_item.view.*

class TripViewHolder(container: View) : RecyclerView.ViewHolder(container) {
    fun bind(item: Trip, f: (Int) -> Unit) {
        val segments = item.trasa.odcinki.map { vSeg -> vSeg.odcinek }
        itemView.apply {
            trip_list_item_name.text = item.nazwa
            trip_list_dates.text = "${item?.dataPocz} - ${item.dataKonc}"
            trip_list_map.segments = segments
            val tripDistance = segments.foldRight(0.0) { x, sum -> x.dlugosc / 1000 + sum }

            trip_list_length.text = "%.2f".format(tripDistance) + " km"

            setOnClickListener {
                f(item.id)
            }
        }
    }
}