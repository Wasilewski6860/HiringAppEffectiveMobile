package ru.hiringapp.vacancy.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.vacancy.api.usecase.ObserveFavouriteVacanciesCountUseCase
import javax.inject.Inject

internal class ObserveFavouriteVacanciesCountUseCaseImpl @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) : ObserveFavouriteVacanciesCountUseCase {
    override fun invoke(): Flow<Int> = vacancyRepository.observeFavouriteVacanciesCount()

}