package com.apusart.got_android.ui.tourist_profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.UserData
import com.apusart.got_android.api.repositories.UserDataRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel : ViewModel() {
    private val userProfileRepository = UserDataRepository()
    val userData = MutableLiveData<Resource<UserData>>()

    fun getUserData(id: Int) {
        viewModelScope.launch {
            try {
                userData.value = Resource.pending()
                userData.value = userProfileRepository.getUserData(id)
            } catch (e: Exception) {
                userData.value = Resource.error(e.message)
            }
        }
    }
}