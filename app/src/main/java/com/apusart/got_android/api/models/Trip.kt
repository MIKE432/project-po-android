package com.apusart.got_android.api.models

import java.util.*

data class Trip(
    val id: Int,
    val nazwa: String,
    val dataPocz: String?,
    val dataKonc: String?,
    val uczestnicy: List<Person>? = listOf(),
    val trasa: Route
)
