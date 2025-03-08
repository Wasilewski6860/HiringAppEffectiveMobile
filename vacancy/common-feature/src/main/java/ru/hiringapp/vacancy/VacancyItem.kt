package ru.hiringapp.vacancy

data class VacancyItem(
    val id: String,
    val lookingNumberText: String?,
    val isFavorite: Boolean,
    val title: String,
    val address: String,
    val company: String,
    val experience: String,
    val publishedDate: String
)