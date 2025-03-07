package ru.hiringapp.vacancy.impl.usecase

import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.vacancy.api.usecase.ChangeVacancyIsFavouriteUseCase
import javax.inject.Inject

internal class ChangeVacancyIsFavouriteUseCaseImpl @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) : ChangeVacancyIsFavouriteUseCase {
    override suspend fun invoke(id: String, isFavourite: Boolean) =
        vacancyRepository.changeVacancyIsFavourite(id, isFavourite)
}