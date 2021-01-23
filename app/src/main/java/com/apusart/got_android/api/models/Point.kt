package com.apusart.got_android.api.models

data class Point(
    val id: Int,
    val nazwa: String,
    val wysokosc: Double,
    val szerokoscGeo: Double,
    val dlugoscGeo: Double,
    val grupa: MountainGroup
)

data class Region(
    val id: Int,
    val region: String
)

data class MountainGroup(
    val kodGrupy: String,
    val nazwa: String,
    val region: Region
)

