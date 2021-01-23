package com.apusart.got_android.api.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apusart.got_android.api.models.Resource
import com.apusart.got_android.api.models.Segment
import com.apusart.got_android.api.services.SegmentService

class SegmentRepository {
    private val segmentService = SegmentService()

    fun getSegments(): LiveData<Resource<List<Segment>>> {
        return liveData {
            emit(segmentService.getSegments())
        }
    }

    suspend fun getSegment(segmentId: Int): Resource<Segment> {
        return segmentService.getSegment(segmentId)
    }

    suspend fun openSegment(segmentId: Int): Resource<Segment> {
        return segmentService.toggleSegment(segmentId, true)
    }

    suspend fun closeSegment(segmentId: Int): Resource<Segment> {
        return segmentService.toggleSegment(segmentId, false)
    }

    suspend fun addSegment(name: String, startPointId: Int, endPointId: Int): Resource<Unit> {
        return segmentService.addSegment(name, startPointId, endPointId)
    }
}