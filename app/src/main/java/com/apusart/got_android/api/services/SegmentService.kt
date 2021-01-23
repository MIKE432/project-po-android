package com.apusart.got_android.api.services

import com.apusart.got_android.api.models.*
import com.apusart.got_android.api.tools.Test
import kotlinx.coroutines.delay

class SegmentService {
    private val service = RetrofitImpl.retrofit

    suspend fun getSegments(): Resource<List<Segment>> {
        return performRequest { service.getSegments() }
    }

    suspend fun getSegment(segmentId: Int): Resource<Segment> {
        return performRequest { service.getSegmentById(segmentId) }
    }

    suspend fun toggleSegment(segmentId: Int, isActive: Boolean): Resource<Segment> {
        return performRequest { service.toggleSegment(segmentId, ToggleSegmentBody(isActive)) }
    }

    suspend fun addSegment(name: String, startPointId: Int, endPointId: Int): Resource<Unit> {
        return performRequest { service.addSegment(name, startPointId, endPointId) }
    }
}