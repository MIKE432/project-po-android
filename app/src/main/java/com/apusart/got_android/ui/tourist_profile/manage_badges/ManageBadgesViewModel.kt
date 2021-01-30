package com.apusart.got_android.ui.tourist_profile.manage_badges

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.got_android.api.models.Badge
import com.apusart.got_android.api.models.ManageBadgesData
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.repositories.UserDataRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ManageBadgesViewModel : ViewModel() {
    private val userDataRepository = UserDataRepository()
    val manageBadgesData = MutableLiveData<Resource<ManageBadgesData>>()

    fun getBadgesData(id: Int) {
        viewModelScope.launch {
            try {
                manageBadgesData.value = Resource.pending()
                manageBadgesData.value = userDataRepository.getManageBadgesData(id)
            } catch (e: Exception) {
                manageBadgesData.value = Resource.error(e.message)
            }
        }
    }

    fun sendNewBadge(item: Badge, bookId: Int, touristId: Int) {
        viewModelScope.launch {
            try {
                manageBadgesData.value = Resource.pending()
                manageBadgesData.value = userDataRepository.addBadge(
                    touristId,
                    item.wyPkt,
                    true,
                    item.stopien,
                    item.rodzaj,
                    bookId
                )
            } catch (e: Exception) {
                manageBadgesData.value = Resource.error(e.message)
            }
        }
    }
}