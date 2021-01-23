package com.apusart.got_android.api.models

data class Segment(
    val id: Int,
    val poczatek: Point,
    val koniec: Point,
    val nazwa: String,
    var czyAktywny: Boolean,
    val dlugosc: Double
)

data class ToggleSegmentBody(
    val czyAktywny: Boolean
)

data class SegmentRequestBody(
    val nazwa: String,
    val poczatek: String,
    val koniec: String
)