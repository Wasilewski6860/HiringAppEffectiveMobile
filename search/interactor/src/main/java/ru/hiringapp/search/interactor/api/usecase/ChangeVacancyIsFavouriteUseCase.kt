package ru.hiringapp.search.interactor.api.usecase

fun interface ChangeVacancyIsFavouriteUseCase {
    suspend operator fun invoke(id: String, isFavourite: Boolean)
}