package ru.hiringapp.domain.vacancy

import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val full: String,
    val short: String? = null
)