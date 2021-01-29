package com.apusart.got_android.api.models

import java.util.*

enum class BadgeType(val typeId: Int) {
    POPULARNA(1),
    WGORY(2),
    MALA(3),
    DUZA(4),
    WYTRWALOSC(5)
}

enum class BadgeLevel(val typeId: Int) {
    BRAZOWA(1),
    SREBRNA(2),
    ZLOTA(3),
    MALA(4),
    DUZA(5)
}


data class Badge(
    val id: Int,
    val dataPrzyznania: Date,
    val wyPkt: Int,
    val czyRozp: Boolean,
    val ksiazeczka: Int,
    val rodzaj: BadgeType,
    val stopien: BadgeLevel
)
