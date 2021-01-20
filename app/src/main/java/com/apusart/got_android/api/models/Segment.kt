package com.apusart.got_android.api.models

data class Segment(
    val IdOdc: Int,
    val IdPoczatek: Point,
    val IdKoniec: Point,
    val Nazwa: String,
    var CzyAktywny: Boolean,
    val Dlugosc: Double
)