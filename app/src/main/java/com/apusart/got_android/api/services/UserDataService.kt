package com.apusart.got_android.api.services


import com.apusart.got_android.api.models.*

class UserDataService {
    private val service = RetrofitImpl.retrofit

    suspend fun getUserProfileData(id: Int): Resource<UserData> {
        return performRequest { service.getUserProfileData(id) }
    }
}
