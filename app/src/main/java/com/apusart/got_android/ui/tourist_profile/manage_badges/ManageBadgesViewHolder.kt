package com.apusart.got_android.ui.tourist_profile.manage_badges

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Badge
import com.apusart.got_android.api.models.BadgeLevel
import com.apusart.got_android.api.models.BadgeType
import kotlinx.android.synthetic.main.order_list_item.view.*

class ManageBadgesViewHolder(container: View) : RecyclerView.ViewHolder(container) {
    fun bind(item: Pair<Badge, Int>, onClickFun: (Badge) -> Unit) {

        itemView.apply {
            badge_image.setImageResource(getBadgeImage(item.first))

            if (item.first.wyPkt > item.second) {
                badge_string.setTextColor(resources.getColor(R.color.error_text_color))
                badge_string.text = "${item.first.wyPkt - item.second} punktów do końca"
            } else {
                badge_string.setTextColor(resources.getColor(R.color.primary_900))
                badge_string.text = "${item.first.wyPkt}/${item.first.wyPkt}"
            }
            setOnClickListener {
                onClickFun(item.first)
            }
        }
    }


    private fun getBadgeImage(item: Badge) =
        when(setOf(BadgeType[item.rodzaj], BadgeLevel[item.stopien])) {
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