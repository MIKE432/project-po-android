package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Segment
import com.apusart.got_android.api.tools.Test
import kotlinx.coroutines.delay

class SegmentService {
    private val service = RetrofitImpl.retrofit


    suspend fun getSegments(): Resource<List<Segment>> {
        //TODO -----------------------

        return Resource.success(Test.segments)
    }

    suspend fun updateSegment() {

    }

    suspend fun getSegment(segmentId: Int): Resource<Segment> {
        return Resource.success(Test.segments[0])
    }

    suspend fun closeSegment(segmentId: Int): Resource<Segment> {
        val segment = Test.segments[1]
        segment.CzyAktywny = false
        delay(1000L)
        return Resource.success(segment)
    }

    suspend fun openSegment(segmentId: Int): Resource<Segment> {
        val segment = Test.segments[1]
        segment.CzyAktywny = true
        delay(1000L)
        return Resource.success(segment)
    }
}