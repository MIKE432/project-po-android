package com.apusart.got_android.api.models

import java.util.*

data class Trip(
    val IdWycieczki: Int,
    val Nazwa: String,
    val DataPocz: Date,
    val DataKonc: Date,
    val Czlonkowie: List<Person> = listOf(),
    val SkladaSieZ: Route
)
