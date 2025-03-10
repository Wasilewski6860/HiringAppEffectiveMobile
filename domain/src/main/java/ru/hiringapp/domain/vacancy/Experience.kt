package ru.hiringapp.domain.vacancy

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val previewText: String,
    val text: String
)