package ru.hiringapp.search.interactor.impl.usecase

import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.usecase.ChangeVacancyIsFavouriteUseCase
import javax.inject.Inject

internal class ChangeVacancyIsFavouriteUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
) : ChangeVacancyIsFavouriteUseCase {
    override suspend fun invoke(id: String, isFavourite: Boolean) =
        searchRepository.changeVacancyIsFavourite(id, isFavourite)
}