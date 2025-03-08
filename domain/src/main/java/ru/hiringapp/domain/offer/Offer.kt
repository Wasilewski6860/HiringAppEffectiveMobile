package ru.hiringapp.domain.offer

data class Offer(
    val id: String? = null,
    val title: String,
    val description: String? = null,
    val link: String,
    val button: Button? = null
)