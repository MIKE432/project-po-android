package com.apusart.got_android.ui.segment_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Segment
import com.apusart.got_android.api.repositories.SegmentRepository
import com.apusart.got_android.api.services.SegmentService
import kotlinx.coroutines.launch
import java.lang.Exception

class SegmentDetailsViewModel : ViewModel() {
    private val segmentRepository = SegmentRepository()
    val segment = MutableLiveData<Resource<Segment>>()


    fun getSegment(segmentId: Int) {
        viewModelScope.launch {
            try {
                segment.value = Resource.pending()
                segment.value = segmentRepository.getSegment(segmentId)
            } catch (e: Exception) {
                segment.value = Resource.error(e.message)
            }
        }
    }

    fun toggleSegment() {
        viewModelScope.launch {
            try {

                val segmentId =
                    segment.value?.data?.IdOdc ?: throw Exception("Nie udało się zmienić statusu odcinka")
                val isActive = segment.value?.data?.CzyAktywny ?: throw Exception("Nie udało się zmienić statusu odcinka")

                segment.value = Resource.pending()

                segment.value =
                    if (isActive)
                        segmentRepository.closeSegment(segmentId)
                    else
                        segmentRepository.openSegment(segmentId)

            } catch (e: Exception) {
                segment.value = Resource.error(e.message)
            }
        }
    }

}