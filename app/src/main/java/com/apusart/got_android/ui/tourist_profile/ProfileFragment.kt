package com.apusart.got_android.ui.tourist_profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.ui.tourist_profile.order_list.OrdersAdapter
import kotlinx.android.synthetic.main.achieved_orders.*
import kotlinx.android.synthetic.main.gained_points_profile_section.*
import kotlinx.android.synthetic.main.profile_main_fragment.*
import kotlinx.android.synthetic.main.trips_profile_section.*

class ProfileFragment : Fragment(R.layout.profile_main_fragment) {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var ordersAdapter: OrdersAdapter
    private val userID = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersAdapter = OrdersAdapter(findNavController())
        ordersAdapter.onClickFun =
            { _ -> ordersAdapter.navController.navigate(R.id.achievedOrdersFragment) }

        achieved_orders_list.apply {
            adapter = ordersAdapter
        }

        setupObservers()
        setupButtons()

        viewModel.getUserData(userID)
        profile_about.setText(R.string.user_description)
    }

    private fun setupButtons() {
        tripsSummary.setOnClickListener {
            findNavController().navigate(R.id.profileTripsFragment);
        }

        achievedOrdersSummary.setOnClickListener {
            findNavController().navigate(R.id.achievedOrdersFragment)
        }

        achievedPointSummary.setOnClickListener {
            findNavController().navigate(R.id.manageBadgesFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        viewModel.userData.observe(viewLifecycleOwner, { res ->
            handleResource(
                res, onSuccess = {
                    val achievedOrders =
                        it?.odznaki?.filter { badge -> badge.dataPrzyznania != null }

                    gained_points_profile_section_points.text = it?.punktacja.toString()
                    textViewNumberTrips.text = it?.wycieczki?.size.toString()
                    textViewNumberBadges.text = achievedOrders?.size.toString()
                    profile_name.text = it?.imie + " " + it?.nazwisko

                    val lastTrip = it?.wycieczki?.last()
                    trips_profile_section_trip_name.text = lastTrip?.nazwa
                    trips_profile_section_trip_date.text =
                        lastTrip?.dataPocz?.replace("-", ".") + " - " + lastTrip?.dataKonc?.replace(
                            "-",
                            "."
                        )

                    val lastTripLength =
                        lastTrip?.trasa?.odcinki?.fold(0.0) { acc, verifiedSegment -> acc + verifiedSegment.odcinek.dlugosc / 1000 }


                    trips_profile_section_trip_length.text = "%.2f".format(lastTripLength) + " km"

                    ordersAdapter.submitList(achievedOrders?.take(4))
                })

        })
    }
}