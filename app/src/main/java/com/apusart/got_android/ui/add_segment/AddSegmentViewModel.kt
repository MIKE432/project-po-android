package com.apusart.got_android.ui.add_segment

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.repositories.SegmentRepository
import com.apusart.got_android.api.services.SegmentService
import kotlinx.coroutines.launch
import java.lang.Exception

class AddSegmentViewModel : ViewModel() {
    private val segmentService = SegmentRepository()

    val segmentNameText = MutableLiveData("")
    val segmentStartIdText = MutableLiveData("")
    val segmentEndIdText = MutableLiveData("")
    val segmentAdded = MutableLiveData<Resource<Unit>>()

    fun isFormValid(): Boolean =
        (segmentNameText.value != "")
                && (segmentStartIdText.value != "")
                && (segmentEndIdText.value != "")

    fun isFormDirty(): Boolean =
        segmentNameText.value != ""
                || segmentStartIdText.value != ""
                || segmentEndIdText.value != ""


    fun addSegment() {
        viewModelScope.launch {
            try {
                segmentAdded.value = Resource.pending()

                segmentAdded.value = segmentService.addSegment(
                    segmentNameText.value!!,
                    segmentStartIdText.value!!.toInt(),
                    segmentEndIdText.value!!.toInt()
                )

            } catch (e: Exception) {
                segmentAdded.value = Resource.error("Nie udało się dodać odcinka")
            }
        }
    }
}