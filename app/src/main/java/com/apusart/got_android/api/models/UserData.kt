package com.apusart.got_android.api.models

data class UserData(
    val imie: String,
    val nazwisko: String,
    val ksiazeczka: Int?,
    val punktacja: Int,
    val odznaki: List<Badge>? = listOf(),
    val wycieczki: List<Trip>
)

data class ManageBadgesData(
    val availablePoints: Int,
    val punktacja: Int,
    val ksiazeczka: Int,
    val odznaki: List<Badge>? = listOf()
)