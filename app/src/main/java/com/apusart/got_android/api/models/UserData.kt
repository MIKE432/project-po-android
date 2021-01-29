package com.apusart.got_android.api.models

data class UserData (
    val imie: String,
    val nazwisko: String,
    val punktacja: Int,
    val odznaki: List<Badge>? = listOf(),
    val wycieczki: List<Trip>
)