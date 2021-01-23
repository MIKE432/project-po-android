package com.apusart.got_android.ui.trip_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private lateinit var alertDialog: AlertDialog.Builder
    private lateinit var memAdapter: MemberAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memAdapter = MemberAdapter()
        trip_details_users.adapter = memAdapter
        setupObservers()
        setupOnClickListeners()
        setupAlertDialog()
        viewModel.getTrip(navArgs.tripId)
    }

    private fun setupObservers() {
        viewModel.trip.observe(viewLifecycleOwner, { res ->
            handleResource(res,
                onSuccess = {
                    trip_details_start_point.text =
                        it?.trasa?.odcinki?.first()?.odcinek?.poczatek?.nazwa ?: "Brak danych"
                    trip_details_end_point.text =
                        it?.trasa?.odcinki?.last()?.odcinek?.koniec?.nazwa ?: "Brak danych"
                    trip_details_name.text = it?.nazwa ?: "Brak danych"
                    trip_details_date.text =
                        "${it?.dataPocz.toString()}    -    ${it?.dataKonc.toString()}"
                    trip_details_map.segments =
                        it?.trasa?.odcinki?.map { vSeg -> vSeg.odcinek } ?: listOf()
                    alertDialog.setMessage(viewModel.trip.value?.data?.nazwa)
                    trip_details_join_trip_modal_text.text = viewModel.trip.value?.data?.nazwa
                    trip_details_users_container.isVisible = it?.uczestnicy?.isEmpty() != true
                    memAdapter.submitList(it?.uczestnicy?.take(1))
                    if (it?.uczestnicy?.size ?: 0 > 1) {
                        trip_details_additional_users.isVisible = true
                        trip_details_additional_users.text = "+ ${it?.uczestnicy?.size?.minus(1)}"
                    }

                    val userIds = it?.uczestnicy?.map { u -> u.id }

                    trip_details_join_trip.isVisible = userIds?.contains(3) != true
                })
        })

        viewModel.joined.observe(viewLifecycleOwner, { res ->
            handleResource(res,
                onSuccess = {
                    trip_details_join_trip.isVisible = false
                    Toast.makeText(context, "Dołączono do wycieczki", Toast.LENGTH_LONG).show()
                    trip_details_joined_trip_modal.isVisible = true
                    join_trip_ok.setOnClickListener { requireActivity().finish() }
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
            alertDialog.show()
        }
    }

    private fun setupAlertDialog() {
        alertDialog = AlertDialog.Builder(requireContext())
        alertDialog
            .setTitle(getString(R.string.join_to_trip_question))
            .setMessage(viewModel.trip.value?.data?.nazwa)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.joinTrip()
            }
            .setNegativeButton(R.string.abort) { dialog, _ ->
                dialog.cancel()
            }
    }
}
