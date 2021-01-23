package com.apusart.got_android.api.models

import java.util.*

data class Person(
    val id: Int,
    val imie: String,
    val nazwisko: String,
    val plec: String,
    val dataUr: Date,
    val email: String,
    val czyAkt: Boolean,
    val miasto: String?,
    val nrDomu: String?,
    val nrMieszk: String?,
    val kodPocztowy: String?,
    val nrTel: String?
)
