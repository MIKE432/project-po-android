package com.apusart.got_android.api.services


import com.apusart.got_android.api.models.*
import retrofit2.http.*

class UserDataService {
    private val service = RetrofitImpl.retrofit

    suspend fun getUserProfileData(id: Int): Resource<UserData> {
        return performRequest { service.getUserProfileData(id) }
    }


    suspend fun getManageBadgesData(id: Int): Resource<ManageBadgesData> {
        return performRequest { service.getManageBadgesData(id) }
    }

    suspend fun addBadge(
        touristId: Int,
        reqPoints: Int,
        isExamined: Boolean,
        level: Int,
        type: Int,
        bookId: Int
    ): Resource<ManageBadgesData> {
        return performRequest { service.addBadge(touristId, reqPoints, isExamined, level, type, bookId) }
    }
}
