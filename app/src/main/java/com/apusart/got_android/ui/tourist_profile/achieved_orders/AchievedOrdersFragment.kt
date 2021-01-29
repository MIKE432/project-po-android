package com.apusart.got_android.ui.tourist_profile.achieved_orders

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.ui.tourist_profile.ProfileViewModel
import com.apusart.got_android.ui.tourist_profile.order_list.OrdersAdapter
import kotlinx.android.synthetic.main.achieved_orders.*
import kotlinx.android.synthetic.main.achieved_orders_fragment.*
import kotlinx.android.synthetic.main.gained_points_profile_section.*
import kotlinx.android.synthetic.main.profile_main_fragment.*
import kotlinx.android.synthetic.main.profile_trips_fragment.*
import kotlinx.android.synthetic.main.trips_profile_section.*
import java.time.ZoneId

class AchievedOrdersFragment : Fragment(R.layout.achieved_orders_fragment) {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var ordersAdapter: OrdersAdapter
    val userID = 2


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersAdapter = OrdersAdapter(findNavController())
        ordersAdapter.textBindFun = { badge ->
            val localDate =
                badge.dataPrzyznania?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
            localDate?.year.toString() ?: ""
        }
        achieved_orders_list.apply {
            adapter = ordersAdapter
        }

        setupObservers()
        viewModel.getUserData(userID)
    }


    private fun setupObservers() {
        viewModel.userData.observe(viewLifecycleOwner, { res ->
            handleResource(
                res, onSuccess = {
                    val achievedOrders =
                        it?.odznaki?.filter { badge -> badge.dataPrzyznania != null }
                    ordersAdapter.submitList(achievedOrders)
                    achieved_orders_count.text = achievedOrders?.size?.toString() ?: "0"
                })

        })

        profile_orders_fragment_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }
    }
}