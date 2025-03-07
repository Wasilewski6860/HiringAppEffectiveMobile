package ru.hiringapp.vacancy.api.usecase

fun interface ChangeVacancyIsFavouriteUseCase {
    suspend operator fun invoke(id: String, isFavourite: Boolean)
}