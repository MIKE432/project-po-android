package com.apusart.got_android.api.models

import java.util.*

enum class BadgeType(val typeId: Int) {
    POPULARNA(1),
    WGORY(2),
    MALA(3),
    DUZA(4),
    WYTRWALOSC(5);

    companion object {
        private val map = values().associateBy(BadgeType::typeId)
        operator fun get(value: Int) = map[value]
    }
}

enum class BadgeLevel(val typeId: Int) {
    BRAZOWA(1),
    SREBRNA(2),
    ZLOTA(3),
    MALA(4),
    DUZA(5);

    companion object {
        private val map = values().associateBy(BadgeLevel::typeId)
        operator fun get(value: Int) = map[value]
    }
}


data class Badge(
    val id: Int?,
    val dataPrzyznania: Date?,
    val wyPkt: Int,
    val czyRozp: Boolean?,
    val ksiazeczka: Int,
    val rodzaj: Int,
    val stopien: Int
)
