package ru.hiringapp.vacancy.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy

fun interface ObserveVacanciesUseCase {
    operator fun invoke(): Flow<List<Vacancy>>
}