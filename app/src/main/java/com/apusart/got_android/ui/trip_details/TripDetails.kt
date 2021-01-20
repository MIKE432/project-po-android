package com.apusart.got_android.ui.trip_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import kotlinx.android.synthetic.main.trip_details.*

class TripDetails : Fragment(R.layout.trip_details) {
    private val viewModel: TripDetailsViewModel by viewModels()
    private val navArgs by navArgs<TripDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupOnClickListeners()
        viewModel.getTrip(navArgs.tripId)
    }

    private fun setupObservers() {
        viewModel.trip.observe(viewLifecycleOwner, { res ->
            handleResource(res,
                onSuccess = {
                    trip_details_start_point.text =
                        it?.SkladaSieZ?.Pomieszcza?.first()?.Odcinek?.Nazwa ?: "Brak danych"
                    trip_details_end_point.text =
                        it?.SkladaSieZ?.Pomieszcza?.last()?.Odcinek?.Nazwa ?: "Brak danych"
                    trip_details_name.text = it?.Nazwa ?: "Brak danych"
                    trip_details_date.text =
                        "${it?.DataPocz.toString()} - ${it?.DataKonc.toString()}"
                    trip_details_map.segments =
                        it?.SkladaSieZ?.Pomieszcza?.map { vSeg -> vSeg.Odcinek } ?: listOf()

                    val memAdapter = MemberAdapter()
                    trip_details_users.adapter = memAdapter

                    memAdapter.submitList(it?.Czlonkowie?.take(2))
                    if (it?.Czlonkowie?.size ?: 0 > 2) {
                        trip_details_additional_users.isVisible = true
                        trip_details_additional_users.text = "+ ${it?.Czlonkowie?.size?.minus(2)}"
                    }
                })
        })

        viewModel.joined.observe(viewLifecycleOwner, { res ->
            handleResource(res,
                onSuccess = {
                    trip_details_join_trip.isVisible = false
                    Toast.makeText(context, "Dołączono do wycieczki", Toast.LENGTH_LONG).show()

                }, onPending = {
                    trip_details_join_trip.transitionToEnd()

                }, onError = { msg, _ ->
                    trip_details_join_trip.transitionToStart()
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                })
        })
    }

    private fun setupOnClickListeners() {
        trip_details_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }

        trip_details_join_trip.setOnClickListener {
            viewModel.joinTrip()
        }
    }

}