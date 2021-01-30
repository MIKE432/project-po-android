package com.apusart.got_android.ui.tourist_profile.order_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Badge
import com.apusart.got_android.api.models.BadgeLevel
import com.apusart.got_android.api.models.BadgeType
import com.apusart.got_android.api.models.Trip
import kotlinx.android.synthetic.main.order_list_item.view.*
import kotlinx.android.synthetic.main.trip_list_item.view.*

class OrdersViewHolder(container: View) : RecyclerView.ViewHolder(container) {
    fun bind(item: Badge, badgeTextBindFun: (Badge) -> String, onClickFun: (Int) -> Unit) {

        itemView.apply {
            badge_image.setImageResource(getBadgeImage(item))
            badge_string.text = badgeTextBindFun(item)

            setOnClickListener {
                onClickFun(item.id!!)
            }
        }
    }

    private fun getBadgeImage(item: Badge) =
        when (setOf(BadgeType[item.rodzaj], BadgeLevel[item.stopien])) {
            setOf(BadgeType.MALA, BadgeLevel.BRAZOWA) -> R.drawable.brazowa_mala
            setOf(BadgeType.MALA, BadgeLevel.SREBRNA) -> R.drawable.srebrna_mala
            setOf(BadgeType.MALA, BadgeLevel.ZLOTA) -> R.drawable.zlota_mala
            setOf(BadgeType.DUZA, BadgeLevel.BRAZOWA) -> R.drawable.brazowa_duza
            setOf(BadgeType.DUZA, BadgeLevel.SREBRNA) -> R.drawable.srebrna_duza
            setOf(BadgeType.DUZA, BadgeLevel.ZLOTA) -> R.drawable.zlota_duza
            setOf(BadgeType.WYTRWALOSC, BadgeLevel.MALA) -> R.drawable.wytrwalosc_mala
            setOf(BadgeType.WYTRWALOSC, BadgeLevel.DUZA) -> R.drawable.wytrwalosc_duza
            else -> R.drawable.popularna
        }
}