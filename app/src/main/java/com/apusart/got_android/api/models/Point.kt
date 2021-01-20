package com.apusart.got_android.api.models

data class Point(
    val IdPunktu: Int,
    val Nazwa: String,
    val Wysokosc: Double,
    val SzerokoscGeo: Double,
    val DlugoscGeo: Double,
    val KodGrupy: MountainGroup
)

data class Region(
    val IdReg: Int,
    val Region: String
)
data class MountainGroup(
    val KodGrupy: String,
    val Nazwa: String,
    val IdReg: Region
)

