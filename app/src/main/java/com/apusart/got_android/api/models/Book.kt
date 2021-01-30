package com.apusart.got_android.api.models

data class Book(
    val id: Int,
    val turysta: Int,
    val odznaki: List<Badge>? = listOf(),
    val punktacja: Int
)
