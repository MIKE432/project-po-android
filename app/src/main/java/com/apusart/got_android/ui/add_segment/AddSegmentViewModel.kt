package com.apusart.got_android.ui.add_segment

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddSegmentViewModel : ViewModel() {
    val segmentNameText = MutableLiveData("")
    val segmentStartIdText = MutableLiveData("")
    val segmentEndIdText = MutableLiveData("")

    fun isFormValid(): Boolean =
                (segmentNameText.value != "")
                && (segmentStartIdText.value != "")
                && (segmentEndIdText.value != "")

    fun isFormDirty(): Boolean =
                 segmentNameText.value != ""
                || segmentStartIdText.value != ""
                || segmentEndIdText.value != ""
}