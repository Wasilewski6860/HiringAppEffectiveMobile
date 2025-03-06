package ru.hiringapp.search.interactor.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy

fun interface ObserveFavouriteVacanciesUseCase {
    operator fun invoke(): Flow<List<Vacancy>>
}