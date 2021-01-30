package com.apusart.got_android.ui.tourist_profile.manage_badges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Badge
import com.apusart.got_android.ui.tourist_profile.order_list.OrdersViewHolder

class ManageBadgesAdapter(val navController: NavController) :
    ListAdapter<Pair<Badge, Int>, ManageBadgesViewHolder>(diffUtil) {
    object diffUtil : DiffUtil.ItemCallback<Pair<Badge, Int>>() {
        override fun areItemsTheSame(
            oldItem: Pair<Badge, Int>,
            newItem: Pair<Badge, Int>
        ): Boolean {
            return oldItem.first.id == newItem.first.id && oldItem.second == newItem.second
        }

        override fun areContentsTheSame(
            oldItem: Pair<Badge, Int>,
            newItem: Pair<Badge, Int>
        ): Boolean {
            return oldItem.first.id == newItem.first.id && oldItem.second == newItem.second
        }
    }

    var itemOnClick: (Badge) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageBadgesViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.manage_order_list_item, parent, false)
        return ManageBadgesViewHolder((container))
    }

    override fun onBindViewHolder(holder: ManageBadgesViewHolder, position: Int) {
        holder.bind(
            getItem(position), onClickFun = itemOnClick
        )
    }


}