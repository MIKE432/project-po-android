package com.apusart.got_android.ui.tourist_profile.trips_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.ui.tourist_profile.ProfileViewModel
import kotlinx.android.synthetic.main.done_trip_details.*
import kotlinx.android.synthetic.main.gained_points_fragment.*
import kotlinx.android.synthetic.main.gained_points_profile_section.*
import kotlinx.android.synthetic.main.profile_trips_fragment.*

class ProfileTripsDetailsFragment : Fragment(R.layout.done_trip_details) {
    private val viewModel: ProfileViewModel by viewModels()
    val userId = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getUserData(userId)
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        viewModel.userData.observe(viewLifecycleOwner, { res ->
            handleResource(
                res, onSuccess = {
                    val trip =
                        it?.wycieczki?.find { trip -> trip.id == arguments?.getInt("tripId") }
                    gained_points_profile_section_points.text = trip?.trasa?.sumaPunkt.toString()
                    doneTripName.text = trip?.nazwa
                    done_trip_fragment_date.text =
                        trip?.dataPocz?.replace("-", ".") + " - " + trip?.dataKonc?.replace("-", ".")

                    var tripLength =
                        trip?.trasa?.odcinki?.fold(0.0) { acc, verifiedSegment -> acc + verifiedSegment.odcinek.dlugosc }

                    if (tripLength != null) {
                        tripLength /= 1000
                    }
                    done_trip_fragment_length.text = "%.2f".format(tripLength) + " km"

                })
        })

        done_trip_fragment_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }
    }
}