package com.apusart.got_android.ui.manage_segment.segment_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Segment

class SegmentAdapter(private val navController: NavController): ListAdapter<Segment, SegmentViewHolder>(diffUtil) {
    object diffUtil: DiffUtil.ItemCallback<Segment>() {
        override fun areItemsTheSame(oldItem: Segment, newItem: Segment): Boolean {
            return oldItem.IdOdc == newItem.IdOdc
        }

        override fun areContentsTheSame(oldItem: Segment, newItem: Segment): Boolean {
            return oldItem.IdOdc == newItem.IdOdc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegmentViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.segment_list_item, parent, false)

        return SegmentViewHolder(container)
    }

    override fun onBindViewHolder(holder: SegmentViewHolder, position: Int) {
        holder.bind(getItem(position)) { id ->
            navController.navigate(SegmentListFragmentDirections.actionSegmentListFragmentToSegmentDetails(id))
        }
    }
}