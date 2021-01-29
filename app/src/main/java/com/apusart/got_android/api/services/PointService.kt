package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.Point
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.performRequest

class PointService {
    private val service = RetrofitImpl.retrofit

    suspend fun getPoints(): Resource<List<Point>> {
        return performRequest { service.getPoints() }
    }
}