package com.apusart.got_android.ui.tourist_profile.manage_badges

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Badge
import com.apusart.got_android.api.models.BadgeLevel
import com.apusart.got_android.api.models.BadgeType
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.ui.tourist_profile.ProfileViewModel
import com.apusart.got_android.ui.tourist_profile.order_list.OrdersAdapter
import com.apusart.got_android.ui.tourist_profile.trips_list.TripsAdapterProfile
import kotlinx.android.synthetic.main.manage_orders_fragment.*
import kotlinx.android.synthetic.main.profile_trips_fragment.*
import java.time.Year

class ManageBadgesFragment : Fragment(R.layout.manage_orders_fragment) {
    private val viewModel: ManageBadgesViewModel by viewModels()
    private lateinit var ordersAdapterAchievable: ManageBadgesAdapter
    private lateinit var ordersAdapterNon: ManageBadgesAdapter
    private val userID = 2

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersAdapterAchievable = ManageBadgesAdapter(findNavController())

        achievable_orders_list.apply {
            adapter = ordersAdapterAchievable
        }

        ordersAdapterNon = ManageBadgesAdapter(findNavController())
        non_achievable_orders_list.apply {
            adapter = ordersAdapterNon
        }

        setupButtons()
        setupObservers()
        viewModel.getBadgesData(userID)
    }

    private fun setupButtons() {
        manage_orders_fragment_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupObservers() {
        viewModel.manageBadgesData.observe(viewLifecycleOwner, { res ->
            handleResource(
                res, onSuccess = {
                    gained_points_count.text = it?.punktacja.toString()
                    gained_points_label.text =
                        getString(R.string.gained_points_in_year) + " " + Year.now().value
                    val achievableOrders =
                        it?.odznaki?.filter { badge -> badge.wyPkt <= it.availablePoints ?: 0 }
                    val nonAchievableOrders =
                        it?.odznaki?.filter { badge -> badge.wyPkt > it.availablePoints ?: 0 }
                    ordersAdapterAchievable.submitList(achievableOrders?.map { badge ->
                        Pair(
                            badge,
                            it.availablePoints
                        )
                    })
                    ordersAdapterNon.submitList(nonAchievableOrders?.map { badge ->
                        Pair(
                            badge,
                            it.availablePoints
                        )
                    })
                    ordersAdapterAchievable.itemOnClick =
                        { badge -> showAlertDialog(badge, it?.ksiazeczka ?: 0) }
                })
        })
    }


    private fun getBadgeName(badge: Badge) =
        when (setOf(BadgeType[badge.rodzaj], BadgeLevel[badge.stopien])) {
            setOf(BadgeType.MALA, BadgeLevel.BRAZOWA) -> "Mała brązowa"
            setOf(BadgeType.MALA, BadgeLevel.SREBRNA) -> "Mała srebrna"
            setOf(BadgeType.MALA, BadgeLevel.ZLOTA) -> "Mała złota"
            setOf(BadgeType.DUZA, BadgeLevel.BRAZOWA) -> "Duża brązowa"
            setOf(BadgeType.DUZA, BadgeLevel.SREBRNA) -> "Duża srebrna"
            setOf(BadgeType.DUZA, BadgeLevel.ZLOTA) -> "Duża złota"
            setOf(BadgeType.WYTRWALOSC, BadgeLevel.MALA) -> "Za Wytrwałość mała"
            setOf(BadgeType.WYTRWALOSC, BadgeLevel.DUZA) -> "Za Wytrwałość duża"
            else -> "Popularna"
        }

    private fun showAlertDialog(badge: Badge, bookId: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog
            .setTitle(getString(R.string.request_badge_text))
            .setMessage(getBadgeName(badge))
            .setPositiveButton(R.string.send) { _, _ ->
                viewModel.sendNewBadge(badge, bookId, userID)
            }
            .setNegativeButton(R.string.abort) { dialog, _ ->
                dialog.cancel()
            }
        alertDialog.show()
    }
}