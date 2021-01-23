package com.apusart.got_android.api.models

import java.util.*

data class VerifiedSegment(
    val id: Int,
    val dataWer: Date?,
    val czyZwer: Boolean?,
    val nrPorz: Int,
    val odcinek: Segment
)
