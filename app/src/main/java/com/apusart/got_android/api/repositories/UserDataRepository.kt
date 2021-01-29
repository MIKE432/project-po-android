package com.apusart.got_android.api.repositories

import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.UserData
import com.apusart.got_android.api.services.UserDataService

class UserDataRepository {
    private val service = UserDataService()

    suspend fun getBookByOwner(id: Int): Resource<UserData> {
        return service.getUserProfileData(id);
    }
}