package com.apusart.got_android.api.models

import java.util.*

data class Route(
    val id: Int,
    val nazwa: String,
    val sumaPunkt: Int,
    val dataPocz: Date,
    val dataKonc: Date,
    val odcinki: List<VerifiedSegment>
)