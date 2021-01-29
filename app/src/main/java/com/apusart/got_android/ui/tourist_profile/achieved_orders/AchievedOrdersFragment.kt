package com.apusart.got_android.ui.tourist_profile.achieved_orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import kotlinx.android.synthetic.main.achieved_orders_fragment.*
import kotlinx.android.synthetic.main.profile_trips_fragment.*

class AchievedOrdersFragment : Fragment(R.layout.achieved_orders_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }


    private fun setupObservers() {

        profile_orders_fragment_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }
    }
}