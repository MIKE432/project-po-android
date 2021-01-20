package com.apusart.got_android.ui.trip_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Person
import com.apusart.got_android.api.models.Segment
import com.apusart.got_android.ui.manage_segment.segment_list.SegmentListFragmentDirections
import com.apusart.got_android.ui.manage_segment.segment_list.SegmentViewHolder

class MemberAdapter(): ListAdapter<Person, MemberViewHolder>(diffUtil) {

    object diffUtil: DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.IdOsoby == newItem.IdOsoby
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.IdOsoby == newItem.IdOsoby
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.member_list_item, parent, false)

        return MemberViewHolder(container)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}