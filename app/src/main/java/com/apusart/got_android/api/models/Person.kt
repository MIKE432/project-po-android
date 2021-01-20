package com.apusart.got_android.api.models

import java.util.*

data class Person(
    val IdOsoby: Int,
    val Imie: String,
    val Nazwisko: String,
    val Plec: String,
    val DataUr: Date,
    val Email: String,
    val CzyAkt: Boolean,
    val Miasto: String?,
    val NrDomu: String?,
    val NrMieszkania: String?,
    val KodPocztowy: String?,
    val NrTel: String?,
    val Wycieczki: List<Trip> = listOf()
)
