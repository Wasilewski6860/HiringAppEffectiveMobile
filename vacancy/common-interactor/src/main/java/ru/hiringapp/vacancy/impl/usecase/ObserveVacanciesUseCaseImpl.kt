package ru.hiringapp.vacancy.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.vacancy.api.usecase.ObserveVacanciesUseCase
import javax.inject.Inject

internal class ObserveVacanciesUseCaseImpl @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) : ObserveVacanciesUseCase {
    override fun invoke(): Flow<List<Vacancy>> = vacancyRepository.observeVacancies()
}