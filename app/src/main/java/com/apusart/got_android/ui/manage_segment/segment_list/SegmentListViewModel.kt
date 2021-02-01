package com.apusart.got_android.ui.manage_segment.segment_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Segment
import com.apusart.got_android.api.repositories.SegmentRepository
import kotlinx.coroutines.launch

class SegmentListViewModel: ViewModel() {
    private val segmentRepository = SegmentRepository()
    val searchText = MutableLiveData<String>()
    val segments = MutableLiveData<Resource<List<Segment>>>()


    fun getSegments() {
        viewModelScope.launch {
            segments.value = segmentRepository.getSegments()
        }
    }
}