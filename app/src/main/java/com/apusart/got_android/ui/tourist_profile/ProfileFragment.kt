package com.apusart.got_android.ui.tourist_profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.ui.tourist_profile.trips_list.ProfileTripsFragment
import kotlinx.android.synthetic.main.gained_points_fragment.view.*
import kotlinx.android.synthetic.main.gained_points_profile_section.*
import kotlinx.android.synthetic.main.profile_main_fragment.*

class ProfileFragment : Fragment(R.layout.profile_main_fragment) {
    private val viewModel: ProfileViewModel by viewModels()
    private val navArgs by navArgs<ProfileFragmentArgs>()
    val userID = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserData(userID)
        setupObservers()
        setupButtons()
        profile_about.setText(R.string.user_description)
    }

    private fun setupButtons() {
        tripsSummary.setOnClickListener {
            findNavController().navigate(R.id.profileTripsFragment);
        }
    }

    private fun setupObservers() {
        viewModel.userData.observe(viewLifecycleOwner, { res ->
            handleResource(
                res, onSuccess = {
                    gained_points_profile_section_points.text = it?.punktacja.toString() ?: "Brak"
                    textViewNumberTrips.text = it?.wycieczki?.size.toString() ?: "0"
                    textViewNumberBadges.text = it?.odznaki?.size.toString() ?: "0"
                    profile_name.text = it?.imie + " " + it?.nazwisko ?: ""

                })
        })
    }
}