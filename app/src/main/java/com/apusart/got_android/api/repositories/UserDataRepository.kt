package com.apusart.got_android.api.repositories

import com.apusart.got_android.api.models.ManageBadgesData
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.UserData
import com.apusart.got_android.api.models.performRequest
import com.apusart.got_android.api.services.UserDataService
import retrofit2.http.Part

class UserDataRepository {
    private val service = UserDataService()

    suspend fun getUserData(id: Int): Resource<UserData> {
        return service.getUserProfileData(id);
    }

    suspend fun getManageBadgesData(id: Int): Resource<ManageBadgesData> {
        return service.getManageBadgesData(id)
    }

    suspend fun addBadge(
        touristId: Int,
        reqPoints: Int,
        isExamined: Boolean,
        level: Int,
        type: Int,
        bookId: Int
    ): Resource<ManageBadgesData> {
        return service.addBadge(touristId,reqPoints, isExamined, level, type, bookId)
    }
}
