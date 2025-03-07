package ru.hiringapp.vacancy.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.vacancy.api.usecase.ObserveVacanciesUseCase
import ru.hiringapp.vacancy.api.usecase.UpdateVacanciesUseCase
import javax.inject.Inject

internal class UpdateVacanciesUseCaseImpl @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) : UpdateVacanciesUseCase {
    override suspend fun invoke(): List<Vacancy> = vacancyRepository.getVacancies()
}