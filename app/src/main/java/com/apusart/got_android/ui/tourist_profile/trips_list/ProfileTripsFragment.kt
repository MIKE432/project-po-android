package com.apusart.got_android.ui.tourist_profile.trips_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.ui.join_trip.trips_list.TripListViewModel
import com.apusart.got_android.ui.join_trip.trips_list.TripsAdapter
import com.apusart.got_android.ui.tourist_profile.ProfileViewModel
import kotlinx.android.synthetic.main.profile_trips_fragment.*
import kotlinx.android.synthetic.main.trips_list_fragment.*

class ProfileTripsFragment : Fragment(R.layout.profile_trips_fragment) {
    private val viewModel: ProfileViewModel by viewModels()
    val userID = 2
    private lateinit var tripsAdapter: TripsAdapterProfile


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tripsAdapter = TripsAdapterProfile(findNavController())
        profile_trips_fragment_trips_list.apply {
            adapter = tripsAdapter
        }
        setupObservers()
        viewModel.getUserData(userID)
    }


    private fun setupObservers() {

        viewModel.userData.observe(viewLifecycleOwner, { res ->
            handleResource(
                res, onSuccess = {
                    profile_trips_fragment_trips_count.text = it?.wycieczki?.size.toString()
                    tripsAdapter.submitList(it?.wycieczki)
                })
        })

        profile_trips_fragment_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }
    }
}