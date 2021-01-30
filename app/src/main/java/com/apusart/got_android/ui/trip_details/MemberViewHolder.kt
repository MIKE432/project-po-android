package com.apusart.got_android.ui.trip_details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apusart.got_android.api.models.Person
import kotlinx.android.synthetic.main.member_list_item.view.*

class MemberViewHolder(container: View) : RecyclerView.ViewHolder(container) {
    fun bind(item: Person) {
        itemView.apply {
            member_list_item_name.text = item.imie + " " + item.nazwisko
        }
    }
}