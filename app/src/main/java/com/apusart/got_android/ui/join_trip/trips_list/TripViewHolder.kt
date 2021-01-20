package com.apusart.got_android.ui.join_trip.trips_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apusart.got_android.api.models.Trip
import kotlinx.android.synthetic.main.trip_list_item.view.*

class TripViewHolder(container: View) : RecyclerView.ViewHolder(container) {
    fun bind(item: Trip, f: (Int) -> Unit) {
        val segments = item.SkladaSieZ.Pomieszcza.map { vSeg -> vSeg.Odcinek }
        itemView.apply {
            trip_list_item_name.text = item.Nazwa
            trip_list_dates.text = "${item?.DataPocz} - ${item.DataKonc}"
            trip_list_map.segments = segments
            trip_list_length.text =
                segments.foldRight(0) { x, sum -> x.Dlugosc.toInt() + sum }.toString() + "km"

            setOnClickListener {
                f(item.IdWycieczki)
            }
        }
    }
}