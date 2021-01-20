package com.apusart.got_android.api.models

import java.util.*

data class Route(
    val IdTrasy: Int,
    val Nazwa: String,
    val SumaPunkt: Int,
    val DataPocz: Date,
    val DataKonc: Date,
    val Pomieszcza: List<VerifiedSegment>
)