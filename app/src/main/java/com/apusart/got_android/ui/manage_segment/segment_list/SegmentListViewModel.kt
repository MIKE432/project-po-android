package com.apusart.got_android.ui.manage_segment.segment_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.repositories.SegmentRepository

class SegmentListViewModel: ViewModel() {
    private val segmentRepository = SegmentRepository()
    val searchText = MutableLiveData<String>()
    val segments = segmentRepository.getSegments()


    fun filterSegments() {

    }
}