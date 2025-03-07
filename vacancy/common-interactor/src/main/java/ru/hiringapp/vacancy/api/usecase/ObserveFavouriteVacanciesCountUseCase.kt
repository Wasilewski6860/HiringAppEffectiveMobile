package ru.hiringapp.vacancy.api.usecase

import kotlinx.coroutines.flow.Flow

fun interface ObserveFavouriteVacanciesCountUseCase {
    operator fun invoke(): Flow<Int>
}