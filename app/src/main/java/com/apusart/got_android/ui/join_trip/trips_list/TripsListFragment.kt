package com.apusart.got_android.ui.join_trip.trips_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import kotlinx.android.synthetic.main.trips_list_fragment.*

class TripsListFragment : Fragment(R.layout.trips_list_fragment) {

    private val viewModel: TripListViewModel by viewModels()
    private lateinit var tripsAdapter: TripsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tripsAdapter = TripsAdapter(findNavController())

        trips_list_fragment_list.apply {
            adapter = tripsAdapter
        }

        setupOnClickListeners()
        setupObservers()
    }

    private fun setupOnClickListeners() {
        trips_list_fragment_header.setOnLeadingIconClickListener {
            requireActivity().finish()
        }
    }

    private fun setupObservers() {
        viewModel.trips.observe(viewLifecycleOwner, { res ->
            handleResource(res,
                onSuccess = {
                    tripsAdapter.submitList(it)
                }, onPending = {

                })
        })
    }
}