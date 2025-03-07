package ru.hiringapp.vacancy.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.vacancy.api.usecase.ObserveFavouriteVacanciesUseCase
import javax.inject.Inject

internal class ObserveFavouriteVacanciesUseCaseImpl @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) : ObserveFavouriteVacanciesUseCase {
    override fun invoke(): Flow<List<Vacancy>> = vacancyRepository.observeFavouriteVacancies()
}