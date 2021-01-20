package com.apusart.got_android.api.models

import java.util.*

data class VerifiedSegment(
    val IdOdcVer: Int,
    val DataWer: Date?,
    val CzyZwer: Boolean?,
    val NrPorz: Int,
    val Odcinek: Segment
)
