package com.apusart.got_android.ui.add_segment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.repositories.PointRepository
import com.apusart.got_android.api.repositories.SegmentRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class AddSegmentViewModel : ViewModel() {
    private val segmentRepository = SegmentRepository()
    private val pointRepository = PointRepository()
    val points = pointRepository.getPoints()
    val segmentNameText = MutableLiveData("")
    val segmentAdded = MutableLiveData<Resource<Unit>>()

    fun isFormValid(): Boolean =
        (segmentNameText.value != "")

    fun isFormDirty(): Boolean =
        segmentNameText.value != ""


    fun addSegment(p1: String, p2: String) {
        viewModelScope.launch {
            try {
                segmentAdded.value = Resource.pending()
                if (p1 == p2)
                    throw Exception("Złe dane")

                segmentAdded.value = segmentRepository.addSegment(
                    segmentNameText.value!!,
                    points.value?.data?.firstOrNull { it.nazwa == p1 }?.id!!,
                    points.value?.data?.firstOrNull { it.nazwa == p2 }?.id!!
                )

            } catch (e: Exception) {
                segmentAdded.value = Resource.error("Nie udało się dodać odcinka")
            }
        }
    }
}