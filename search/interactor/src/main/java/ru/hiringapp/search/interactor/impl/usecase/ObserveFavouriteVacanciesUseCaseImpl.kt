package ru.hiringapp.search.interactor.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.usecase.ObserveFavouriteVacanciesUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveOffersUseCase
import javax.inject.Inject

internal class ObserveFavouriteVacanciesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
) : ObserveFavouriteVacanciesUseCase {
    override fun invoke(): Flow<List<Vacancy>> = searchRepository.observeFavouriteVacancies()
}