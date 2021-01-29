package com.apusart.got_android.api.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apusart.got_android.api.models.Point
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.services.PointService

class PointRepository {
    private val pointService = PointService()

    fun getPoints(): LiveData<Resource<List<Point>>> {
        return liveData { emit(pointService.getPoints()) }
    }
}