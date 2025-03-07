package ru.hiringapp.vacancy.api.usecase

import ru.hiringapp.domain.vacancy.Vacancy

fun interface UpdateVacanciesUseCase {
    suspend operator fun invoke(): List<Vacancy>
}