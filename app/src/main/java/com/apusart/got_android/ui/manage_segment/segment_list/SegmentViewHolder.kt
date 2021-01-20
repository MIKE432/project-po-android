package com.apusart.got_android.ui.manage_segment.segment_list

import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.apusart.got_android.R
import com.apusart.got_android.api.models.Segment
import kotlinx.android.synthetic.main.segment_list_item.view.*

class SegmentViewHolder(container: View): RecyclerView.ViewHolder(container) {
    fun bind(item: Segment, onClickListener: (Int) -> Unit) {
        itemView.apply {
            setOnClickListener {
                onClickListener(item.IdOdc)
            }
            segment_list_item_id.text = resources.getString(R.string.segment_id, item.IdOdc)
            segment_list_item_is_active.text = resources.getString(R.string.segment_status, if(item.CzyAktywny) "Otwarty" else "Zamknięty")
            segment_list_item_length.text = resources.getString(R.string.segment_length, item.Dlugosc.toString() + "km")
            segment_list_item_name.text = resources.getString(R.string.segment_name, item.Nazwa)
            segment_list_item_title.text = item.IdKoniec.KodGrupy.Nazwa
            segment_list_fragment_map.segments = listOf(item)
        }
    }
}