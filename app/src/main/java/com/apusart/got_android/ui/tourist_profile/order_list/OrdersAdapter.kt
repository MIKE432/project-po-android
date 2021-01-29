package com.apusart.got_android.ui.tourist_profile.order_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Badge
import com.apusart.got_android.api.models.Trip

open class OrdersAdapter(val navController: NavController) :
    ListAdapter<Badge, OrdersViewHolder>(diffUtil) {
    object diffUtil : DiffUtil.ItemCallback<Badge>() {
        override fun areItemsTheSame(oldItem: Badge, newItem: Badge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Badge, newItem: Badge): Boolean {
            return oldItem.id == newItem.id
        }
    }

    var textBindFun: (Badge) -> String = {""}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_list_item, parent, false)

        return OrdersViewHolder((container))
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            badgeTextBindFun = textBindFun,
            onClickFun = {})
    }
}